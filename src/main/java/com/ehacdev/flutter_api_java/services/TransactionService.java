package com.ehacdev.flutter_api_java.services;

import java.util.List;
import java.util.UUID;

import com.ehacdev.flutter_api_java.datas.entities.Transaction;
import com.ehacdev.flutter_api_java.datas.enums.TransactionType;
import com.ehacdev.flutter_api_java.web.dto.request.CreditRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.request.TransactionRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.response.TransactionResponseDTO;

public interface TransactionService {

    List<TransactionResponseDTO> getTransactionsCurrentUser();

     UUID parseId(String id);

    Transaction getTransactionById(String id);

    TransactionResponseDTO processTransaction(
        TransactionRequestDTO request, 
        TransactionType type
    );

    TransactionResponseDTO processTransaction(CreditRequestDTO request)    ;

}
