package com.ehacdev.flutter_api_java.services.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.ehacdev.flutter_api_java.datas.entities.Account;
import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.datas.repositories.AccountRepository;
import com.ehacdev.flutter_api_java.services.AccountService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl  implements AccountService {
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
}
