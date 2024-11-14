package com.ehacdev.flutter_api_java.web.mappers.impl;

import com.ehacdev.flutter_api_java.datas.entities.CreditPurchase;
import com.ehacdev.flutter_api_java.web.dto.response.CreditPurchaseDTO;

public class CreditPurchaseMapper {

     public static CreditPurchaseDTO toDto(CreditPurchase entity) {
        if (entity == null) return null;
        return new CreditPurchaseDTO(
            entity.getReceiverName(),
            entity.getReceiverPhoneNumber(),
            entity.getReceiverEmail()
        );
    }

}
