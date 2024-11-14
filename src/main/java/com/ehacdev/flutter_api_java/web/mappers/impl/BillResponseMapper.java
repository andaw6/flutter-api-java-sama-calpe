package com.ehacdev.flutter_api_java.web.mappers.impl;

import com.ehacdev.flutter_api_java.datas.entities.Bill;
import com.ehacdev.flutter_api_java.web.dto.response.BillResponseDTO;

public class BillResponseMapper {
    public static BillResponseDTO toDto(Bill entity) {
        return new BillResponseDTO(
            entity.getId(),
            entity.getAmount(),
            entity.getCurrency(),
            entity.getStatus(),
            CompanyResponseMapper.toDto(entity.getCompany())  // Assuming Bill has Company
        );
    }


}
