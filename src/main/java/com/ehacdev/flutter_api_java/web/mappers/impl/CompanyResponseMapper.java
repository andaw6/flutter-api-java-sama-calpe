package com.ehacdev.flutter_api_java.web.mappers.impl;

import com.ehacdev.flutter_api_java.datas.entities.Company;
import com.ehacdev.flutter_api_java.web.dto.response.CompanyResponseDTO;

public class CompanyResponseMapper {

     public static CompanyResponseDTO toDto(Company entity) {
        return new CompanyResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getType(),
            entity.getIcon(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

}
