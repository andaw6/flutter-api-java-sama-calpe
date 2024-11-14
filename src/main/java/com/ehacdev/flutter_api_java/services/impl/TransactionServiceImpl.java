package com.ehacdev.flutter_api_java.services.impl;

import com.ehacdev.flutter_api_java.datas.entities.Transaction;
import com.ehacdev.flutter_api_java.datas.repositories.TransactionRepository;
import com.ehacdev.flutter_api_java.security.AuthService;
import com.ehacdev.flutter_api_java.services.TransactionService;
import com.ehacdev.flutter_api_java.web.dto.response.TransactionResponseDTO;
import com.ehacdev.flutter_api_java.web.mappers.impl.TransactionResponseMapper;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final AuthService authService;
    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionResponseDTO> getTransactionsCurrentUser() {
        UserDetails userAuth = authService.userAuth();
        List<Transaction> transactions = transactionRepository.getTransactionsByUserId(userAuth.getUsername());
        return transactions.stream()
                .map(TransactionResponseMapper::toDto).toList();
    }

}
