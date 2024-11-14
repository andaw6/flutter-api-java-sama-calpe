package com.ehacdev.flutter_api_java.datas.entities;


import com.ehacdev.flutter_api_java.datas.enums.BillStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "bills")
public class Bill extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "companyId", nullable = false)
    private Company company;

    private double amount;

    private String currency = "XOR";

    @Enumerated(EnumType.STRING)
    private BillStatus status;
}
