package com.ehacdev.flutter_api_java.web.mappers.impl;

import com.ehacdev.flutter_api_java.datas.entities.Notification;
import com.ehacdev.flutter_api_java.web.dto.response.NotificationResponseDTO;

public class NotificationResponseMapper {

    public static NotificationResponseDTO toDto(Notification entity) {
        if (entity == null)
            return null;
        return new NotificationResponseDTO(
            entity.getId(),
            entity.getUser().getId(),
            entity.getMessage(),
            entity.getIsRead(),
            entity.getCreatedAt(),
            entity.getReadAt()
        );
    }
}
