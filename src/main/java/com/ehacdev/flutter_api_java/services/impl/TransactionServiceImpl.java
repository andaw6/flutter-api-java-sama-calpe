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
import com.ehacdev.flutter_api_java.services.TransactionService;
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
        UUID transactionId = parseId(id);
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found."));
    }

    public UUID parseId(String id) {
        try {
            return UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid UUID format.", e);
        }
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
        return performTransaction(transactionType, senderPhoneNumber, receiverPhoneNumber, request, senderAccount,
                receiverAccount);
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
        Transaction transaction = buildTransaction(senderAccount, receiverAccount, request, transactionType);
        return transactionRepository.save(transaction);
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
