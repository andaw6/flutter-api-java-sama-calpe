package com.ehacdev.flutter_api_java.web.dto.response;

import java.util.Date;
import java.util.UUID;

import com.ehacdev.flutter_api_java.datas.enums.BillStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillResponseDTO {
    private UUID id;
    private UUID userId;
    private double amount;
    private String currency;
    private BillStatus status;
    private Date createdAt;
    private CompanyResponseDTO company;
    private Date updatedAt;
}
