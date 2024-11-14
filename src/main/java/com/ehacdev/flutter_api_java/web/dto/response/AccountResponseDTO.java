package com.ehacdev.flutter_api_java.web.dto.response;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDTO {
    private UUID id;
    private double balance;
    private String currency;
    private String qrCode;
    private boolean isActive;
    private BigDecimal plafond;
    private Date createdAt;
    private Date updatedAt;
}
