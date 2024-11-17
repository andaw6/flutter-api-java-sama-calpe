package com.ehacdev.flutter_api_java.services.impl;

import com.ehacdev.flutter_api_java.datas.entities.Account;
import com.ehacdev.flutter_api_java.datas.entities.Transaction;
import com.ehacdev.flutter_api_java.datas.enums.TransactionStatus;
import com.ehacdev.flutter_api_java.datas.enums.TransactionType;
import com.ehacdev.flutter_api_java.datas.enums.UserRole;
import com.ehacdev.flutter_api_java.datas.repositories.TransactionRepository;
import com.ehacdev.flutter_api_java.security.AuthService;
import com.ehacdev.flutter_api_java.services.AccountService;
import com.ehacdev.flutter_api_java.services.CreditPurchaseService;
import com.ehacdev.flutter_api_java.services.NotificationService;
import com.ehacdev.flutter_api_java.services.TransactionService;
import com.ehacdev.flutter_api_java.utils.Utils;
import com.ehacdev.flutter_api_java.web.dto.request.CreditRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.request.TransactionRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.response.TransactionResponseDTO;
import com.ehacdev.flutter_api_java.web.mappers.impl.TransactionResponseMapper;

import jakarta.transaction.Transactional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AuthService authService;
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;
    private final CreditPurchaseService creditPurchaseService;
    private final NotificationService notificationService;

    @Override
    public List<TransactionResponseDTO> getTransactionsCurrentUser() {
        UserDetails userAuth = authService.userAuth();
        List<Transaction> transactions = transactionRepository.getTransactionsByUserId(userAuth.getUsername());
        return transactions.stream()
                .map(TransactionResponseMapper::toDto)
                .toList();
    }

    @Override
    public Transaction getTransactionById(String id) {
        UUID transactionId = Utils.convertUUID(id);
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found."));
    }

    @Transactional
    public TransactionResponseDTO processTransaction(CreditRequestDTO request) {

        String senderPhoneNumber = authService.userAuth().getUsername();
        Account senderAccount = accountService.findAccountByPhoneNumber(senderPhoneNumber);
        validateBalance(senderAccount, request.getAmount() + request.getFeeAmount());
        Transaction transaction = buildPurchaseTransaction(senderAccount, request);
        transaction.setCreditPurchase(
                creditPurchaseService.create(
                        transaction,
                        request.getName(),
                        request.getPhone(),
                        Optional.ofNullable(request.getEmail())));

        accountService.debit(senderPhoneNumber, request.getAmount() + request.getFeeAmount());
        transaction.setStatus(TransactionStatus.COMPLETED);
        transactionRepository.save(transaction);
        creditPurchaseService.save(transaction.getCreditPurchase());
        notificationService.createNotification("Vous venez d'acheter " + request.getAmount() + "F de crédit au numéro ("
                + request.getPhone() + ")\n" + "Nouveau solde: "
                + (senderAccount.getBalance() - (request.getAmount() + request.getFeeAmount())) + "F");
        return TransactionResponseMapper.toDto(transaction);
    }

    public TransactionResponseDTO processTransaction(TransactionRequestDTO request, TransactionType type) {
        Map<String, String> data = new HashMap<>();
        data.put("phone", request.getPhone());
        String phoneKey = getUserPhoneKey(type);
        try {
            data = validateData(phoneKey, data);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la validation des données : " + e.getMessage());
        }
        Transaction transaction = createTransaction(data.get("senderPhoneNumber"), data.get("receiverPhoneNumber"),
                type, request);

        return TransactionResponseMapper.toDto(transaction);
    }

    
    private Transaction createTransaction(
            String senderPhoneNumber,
            String receiverPhoneNumber,
            TransactionType transactionType,
            TransactionRequestDTO request) {
        Map<String, Account> accounts = accountService.validateAccounts(senderPhoneNumber, receiverPhoneNumber);
        Account senderAccount = accounts.get("sender");
        Account receiverAccount = accounts.get("receiver");
        validateAccountAvailability(senderAccount, receiverAccount, transactionType, request);
        Transaction transaction = performTransaction(transactionType, senderPhoneNumber, receiverPhoneNumber, request,
                senderAccount, receiverAccount);
        transaction.setStatus(TransactionStatus.COMPLETED);
        transactionRepository.save(transaction);
        notify(transactionType, request.getAmount(), request.getFeeAmount(), senderAccount, receiverAccount);
        return transaction;
    }

    private void notify(TransactionType type, double amount, double feeAmount, Account sender, Account receiver) {
        String msg0 = null;
        String msg1 = null;
        String receiverPhone = receiver.getUser().getPhoneNumber();
        String senderPhone = sender.getUser().getPhoneNumber();
        String receiverName = receiver.getUser().getName();
        String senderName = sender.getUser().getName();
        double senderBalance = sender.getBalance() - (amount + feeAmount);
        double receiverBalance = receiver.getBalance() + amount;

        switch (type) {
            case DEPOSIT:
                msg0 = "Vous venez d'envoyer " + amount + "F à " + receiverName + " ("
                        + receiverPhone + ")\n" + "Nouveau solde: "
                        + senderBalance + "F";
                msg1 = "Vous venez de recevoir un dépot de " + amount + "F De " + senderName + " ("
                        + senderPhone + ")\n" + "Nouveau solde: "
                        + receiverBalance + "F";
                break;
            case WITHDRAW:
                msg1 = "Vous avez d'envoyer " + amount + "F à " + receiverName + " ("
                        + receiverPhone + ")\n" + "Nouveau solde: "
                        + senderBalance + "F";
                msg0 = "Vous venez retirer " + amount + "F sur votre comptes.\n" + "Nouveau solde: "
                        + receiverBalance + "F";
                break;
            case TRANSFER:
                msg0 = "Vous avez envoyer " + amount + "F à " + receiverName + " ("
                        + receiverPhone + ")\n" + "Nouveau solde: "
                        + senderBalance + "F";
                msg1 = "Vous avez récu " + amount + "F De " + senderName + " ("
                        + senderPhone + ")\n" + "Nouveau solde: "
                        + receiverBalance + "F";
                break;
            case PURCHASE:
            default:
                break;
        }
        notificationService.createNotification(msg0);
        notificationService.createNotification(receiver.getUser(), msg1);
    }

    @Transactional
    private Transaction performTransaction(
            TransactionType transactionType,
            String senderPhoneNumber,
            String receiverPhoneNumber,
            TransactionRequestDTO request,
            Account senderAccount,
            Account receiverAccount) {
        if (transactionType == TransactionType.WITHDRAW || transactionType == TransactionType.TRANSFER) {
            accountService.debit(senderPhoneNumber, request.getAmount() + request.getFeeAmount());
            accountService.credit(receiverPhoneNumber, request.getAmount());
        } else if (transactionType == TransactionType.DEPOSIT) {
            accountService.credit(receiverPhoneNumber, request.getAmount());
            accountService.debit(senderPhoneNumber, request.getAmount() + request.getFeeAmount());
        }
        return transactionRepository.save(buildTransaction(senderAccount, receiverAccount, request, transactionType));
    }


    private void validateAccountAvailability(Account senderAccount, Account receiverAccount,
            TransactionType transactionType, TransactionRequestDTO request) {
        if (senderAccount == null || receiverAccount == null) {
            throw new RuntimeException("Compte de l'expéditeur ou du destinataire introuvable.");
        }
        if (senderAccount.getUser().getRole() == UserRole.ADMIN
                || receiverAccount.getUser().getRole() == UserRole.AGENT) {
            throw new RuntimeException("Ce numéro ne peut pas effectuer ou recevoir des transactions.");
        }
        if (transactionType == TransactionType.TRANSFER &&
                senderAccount.getBalance() < (request.getAmount() + request.getFeeAmount())) {
            throw new RuntimeException("Fonds insuffisants.");
        }
    }

    private void validateBalance(Account account, double amount) {
        if (account.getBalance() < amount) {
            throw new RuntimeException("Fonds insuffisants.");
        }
    }

    private Transaction buildTransaction(Account senderAccount, Account receiverAccount,
            TransactionRequestDTO request, TransactionType transactionType) {
        return Transaction.builder()
                .sender(senderAccount.getUser())
                .receiver(receiverAccount.getUser())
                .amount(request.getAmount())
                .feeAmount(request.getFeeAmount())
                .transactionType(transactionType)
                .status(TransactionStatus.PENDING)
                .currency(request.getCurrency())
                .build();
    }

    private Transaction buildPurchaseTransaction(Account senderAccount, CreditRequestDTO request) {
        return Transaction.builder()
                .sender(senderAccount.getUser())
                .amount(request.getAmount())
                .feeAmount(request.getFeeAmount())
                .currency(request.getCurrency())
                .transactionType(TransactionType.PURCHASE)
                .status(TransactionStatus.PENDING)
                .build();
    }

    private String getUserPhoneKey(TransactionType type) {
        switch (type) {
            case WITHDRAW:
                return "receiverPhoneNumber";
            case TRANSFER:
            case DEPOSIT:
            default:
                return "senderPhoneNumber";
        }
    }

    private Map<String, String> validateData(String key, Map<String, String> data) throws Exception {
        String phone = authService.userAuth().getUsername();
        if (phone.equals(data.get("phone"))) {
            throw new Exception("L'expéditeur et le destinataire ne peuvent pas être identiques.");
        }
        if ("senderPhoneNumber".equals(key)) {
            data.put("receiverPhoneNumber", data.get("phone"));
        } else {
            data.put("senderPhoneNumber", data.get("phone"));
        }
        data.remove("phone");
        data.put(key, phone);
        return data;
    }

}
