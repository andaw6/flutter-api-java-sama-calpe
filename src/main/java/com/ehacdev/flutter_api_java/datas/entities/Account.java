package com.ehacdev.flutter_api_java.datas.entities;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "userId", nullable = false, unique = true)
    private User user;

    private double balance = 0;
    private String currency = "XOR";
    private String qrCode;
    private boolean isActive = true;
    private BigDecimal plafond = new BigDecimal("500000");
}
