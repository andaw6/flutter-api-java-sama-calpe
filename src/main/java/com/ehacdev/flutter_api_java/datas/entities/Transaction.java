package com.ehacdev.flutter_api_java.datas.entities;

import lombok.*;


import com.ehacdev.flutter_api_java.datas.enums.TransactionStatus;
import com.ehacdev.flutter_api_java.datas.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
@JsonIgnoreProperties({"sender", "receiver"})
public class Transaction extends BaseEntity{
    private double amount;
    
    @ManyToOne
    @JoinColumn(name = "senderId", nullable = false)
    private User sender;

    @ManyToOne
    @JoinColumn(name = "receiverId")
    private User receiver;

    @Builder.Default
    private double feeAmount = 0;

    @Builder.Default
    private String currency = "XOR";

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @OneToOne(mappedBy = "transaction")
    private CreditPurchase creditPurchase;
}
