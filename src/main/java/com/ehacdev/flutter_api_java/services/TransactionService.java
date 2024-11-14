package com.ehacdev.flutter_api_java.services;

import java.util.List;

import com.ehacdev.flutter_api_java.web.dto.response.TransactionResponseDTO;

public interface TransactionService {

    List<TransactionResponseDTO> getTransactionsCurrentUser();
}
