package com.ehacdev.flutter_api_java.web.dto.response;

import java.util.Date;
import java.util.UUID;

import com.ehacdev.flutter_api_java.datas.enums.NotificationStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponseDTO {
    private UUID id;
    private UUID userId;
    private String message;
    private NotificationStatus isRead = NotificationStatus.UNREAD;
    private Date createdAt;
    private Date readAt = null;
}
