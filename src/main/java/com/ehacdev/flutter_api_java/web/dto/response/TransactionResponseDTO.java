package com.ehacdev.flutter_api_java.web.dto.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.ehacdev.flutter_api_java.datas.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
    private UUID id;
    private double amount;
    private double feeAmount;
    private String currency;
    private TransactionType transactionType;
    private Date createdAt;
    private Date updatedAt;
    private UserInfoDTO sender;
    private Optional<UserInfoDTO> receiver;
    private Optional<CreditPurchaseDTO> creditPurchase;
}
