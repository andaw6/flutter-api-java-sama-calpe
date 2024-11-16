package com.ehacdev.flutter_api_java.services.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ehacdev.flutter_api_java.datas.entities.Account;
import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.datas.repositories.AccountRepository;
import com.ehacdev.flutter_api_java.exceptions.InsufficientFundsException;
import com.ehacdev.flutter_api_java.exceptions.ModelNotFoundException;
import com.ehacdev.flutter_api_java.exceptions.OverLimitException;
import com.ehacdev.flutter_api_java.services.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public Account createAccountForUser(User user) {
        // Création d'un compte pour l'utilisateur
        Account account = Account.builder()
                .user(user) // Associer l'utilisateur créé
                .balance(0)
                .currency("XOR")
                .isActive(true)
                .plafond(new BigDecimal("500000"))
                .build();
        return accountRepository.save(account);
    }

    public Account debit(String phoneNumber, double amount) {
        Account account = findAccountByPhoneNumber(phoneNumber);

        if (account.getBalance() < amount) {
            throw new InsufficientFundsException("Fonds insuffisants.");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        return account;
    }

    public Account credit(String phoneNumber, double amount) {
        Account account = findAccountByPhoneNumber(phoneNumber);

        double newBalance = account.getBalance() + amount;
        if (newBalance > account.getPlafond().doubleValue()) {
            throw new OverLimitException(
                    String.format("Impossible de créditer le compte : le solde limite de %.2f FCFA est dépassé.",
                            account.getPlafond()));
        }

        account.setBalance(newBalance);
        accountRepository.save(account);

        return account;
    }

    public Map<String, Account> validateAccounts(String senderPhoneNumber, String receiverPhoneNumber) {
        Map<String, Account> accounts = new HashMap<>();
        accounts.put("sender", findAccountByPhoneNumber(senderPhoneNumber));
        accounts.put("receiver", findAccountByPhoneNumber(receiverPhoneNumber));
        return accounts;
    }

    public Account getAccountByUser(String userId) {
        return accountRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ModelNotFoundException("Compte non trouvé."));
    }

    public Account findAccountByPhoneNumber(String phoneNumber) {
        return accountRepository.findByUserPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ModelNotFoundException("Compte non trouvé."));
    }

}
