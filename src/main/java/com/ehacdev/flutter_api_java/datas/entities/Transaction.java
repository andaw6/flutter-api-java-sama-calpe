package com.ehacdev.flutter_api_java.datas.entities;

import lombok.*;

import java.math.BigDecimal;

import com.ehacdev.flutter_api_java.datas.enums.TransactionStatus;
import com.ehacdev.flutter_api_java.datas.enums.TransactionType;

import jakarta.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction extends BaseEntity{
    private double amount;
    
    @ManyToOne
    @JoinColumn(name = "senderId", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiver;

    private BigDecimal feeAmount = BigDecimal.ZERO;
    
    private String currency = "XOR";

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @OneToOne(mappedBy = "transaction")
    private CreditPurchase creditPurchase;
}
