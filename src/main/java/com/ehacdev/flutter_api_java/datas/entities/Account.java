package com.ehacdev.flutter_api_java.datas.entities;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts")
@JsonIgnoreProperties({"user"})
public class Account extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "userId", nullable = false, unique = true)
    private User user;

    @Builder.Default
    private double balance = 0;
    @Builder.Default
    private String currency = "XOR";
    private String qrCode;
    @Builder.Default
    private boolean isActive = true;
    @Builder.Default
    private BigDecimal plafond = new BigDecimal("500000");
}
