package com.ehacdev.flutter_api_java.web.mappers.impl;

import com.ehacdev.flutter_api_java.datas.entities.Account;
import com.ehacdev.flutter_api_java.web.dto.response.AccountResponseDTO;

public class AccountResponseMapper {
     public static AccountResponseDTO toDto(Account entity) {
        return new AccountResponseDTO(
            entity.getId(),
            entity.getBalance(),
            entity.getCurrency(),
            entity.getQrCode(),
            entity.isActive(),
            entity.getPlafond(),
            entity.getCreatedAt(),
            entity.getUpdatedAt(),
            entity.getUser().getId()
        );
    }

}
