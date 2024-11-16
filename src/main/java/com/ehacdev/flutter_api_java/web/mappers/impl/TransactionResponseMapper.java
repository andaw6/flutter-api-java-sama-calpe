package com.ehacdev.flutter_api_java.web.mappers.impl;

import java.util.Optional;

import com.ehacdev.flutter_api_java.datas.entities.Transaction;
import com.ehacdev.flutter_api_java.web.dto.response.TransactionResponseDTO;

public class TransactionResponseMapper {

    public static TransactionResponseDTO toDto(Transaction entity) {
       return TransactionResponseDTO.builder()
           .id(entity.getId())
           .amount(entity.getAmount())
           .feeAmount(entity.getFeeAmount())
           .currency(entity.getCurrency())
           .type(entity.getTransactionType())
           .createdAt(entity.getCreatedAt())
           .updatedAt(entity.getUpdatedAt())
           .sender(UserInfoMapper.toDto(entity.getSender())) 
           .receiver(Optional.ofNullable(UserInfoMapper.toDto(entity.getReceiver()))) 
           .creditPurchase(Optional.ofNullable(CreditPurchaseMapper.toDto(entity.getCreditPurchase()))) 
           .status(entity.getStatus())
           .build();
   }
}
