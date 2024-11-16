package com.ehacdev.flutter_api_java.web.controllers.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehacdev.flutter_api_java.datas.enums.TransactionType;
import com.ehacdev.flutter_api_java.services.TransactionService;
import com.ehacdev.flutter_api_java.web.dto.request.CreditRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.request.TransactionRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.response.TransactionResponseDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


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

    @PostMapping("transfer")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')") 
    public ResponseEntity<TransactionResponseDTO> createTransfer(@Valid @RequestBody TransactionRequestDTO request) {
        return ResponseEntity.ok(transactionService.processTransaction(request, TransactionType.TRANSFER));
    }
    
    
    @PostMapping("deposit")
    @PreAuthorize("hasRole('VENDOR')") 
    public ResponseEntity<TransactionResponseDTO> createDeposit(@Valid @RequestBody TransactionRequestDTO request) {
        return ResponseEntity.ok(transactionService.processTransaction(request, TransactionType.DEPOSIT));
    }
    

    @PostMapping("withdraw")
    @PreAuthorize("hasRole('VENDOR')") 
    public ResponseEntity<TransactionResponseDTO> createWithdraw(@Valid @RequestBody TransactionRequestDTO request) {
        return ResponseEntity.ok(transactionService.processTransaction(request, TransactionType.WITHDRAW));
    }
    

    @PostMapping("purchase")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')") 
    public ResponseEntity<TransactionResponseDTO> createPurchase(@Valid @RequestBody CreditRequestDTO request) {
        return ResponseEntity.ok(transactionService.processTransaction(request));
    }
    
    
}
