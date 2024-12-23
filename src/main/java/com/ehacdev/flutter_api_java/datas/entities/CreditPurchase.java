package com.ehacdev.flutter_api_java.datas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "credit_purchases")
@JsonIgnoreProperties({"transaction"})
public class CreditPurchase extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "transactionId", nullable = false, unique = true)
    private Transaction transaction;

    private String receiverName;
   
    private String receiverPhoneNumber;
   
    private String receiverEmail;

}
