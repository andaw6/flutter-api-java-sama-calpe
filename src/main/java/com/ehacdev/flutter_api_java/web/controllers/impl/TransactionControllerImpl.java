package com.ehacdev.flutter_api_java.web.controllers.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehacdev.flutter_api_java.services.TransactionService;
import com.ehacdev.flutter_api_java.web.dto.response.TransactionResponseDTO;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transactions")
public class TransactionControllerImpl {

    private final TransactionService transactionService;

     @GetMapping("current")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')")  
    public ResponseEntity<List<TransactionResponseDTO>> getMethodName() {
        return ResponseEntity.ok(transactionService.getTransactionsCurrentUser());
    }
    
}
