package com.ehacdev.flutter_api_java.web.dto.response;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.ehacdev.flutter_api_java.datas.enums.TransactionStatus;
import com.ehacdev.flutter_api_java.datas.enums.TransactionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
    private UUID id;
    private double amount;
    private double feeAmount;
    private String currency;
    private TransactionType type;
    private TransactionStatus status;
    private Date createdAt;
    private Date updatedAt;
    private UserInfoDTO sender;
    private Optional<UserInfoDTO> receiver;  // Optional pour gére  r les objets null
    private Optional<CreditPurchaseDTO> creditPurchase; 
}
