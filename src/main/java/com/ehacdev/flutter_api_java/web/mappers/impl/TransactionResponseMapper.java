package com.ehacdev.flutter_api_java.web.mappers.impl;

import java.util.Optional;

import com.ehacdev.flutter_api_java.datas.entities.Transaction;
import com.ehacdev.flutter_api_java.web.dto.response.TransactionResponseDTO;

public class TransactionResponseMapper {

     public static TransactionResponseDTO toDto(Transaction entity) {
        return new TransactionResponseDTO(
            entity.getId(),
            entity.getAmount(),
            entity.getFeeAmount(),
            entity.getCurrency(),
            entity.getTransactionType(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            UserInfoMapper.toDto(entity.getSender()),
            Optional.ofNullable(UserInfoMapper.toDto(entity.getReceiver())),
            Optional.ofNullable(CreditPurchaseMapper.toDto(entity.getCreditPurchase()))
        );
    }

}
