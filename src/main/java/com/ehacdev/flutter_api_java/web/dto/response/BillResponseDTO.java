package com.ehacdev.flutter_api_java.web.dto.response;

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
    private double amount;
    private String currency;
    private BillStatus status;
    private CompanyResponseDTO company;
}
