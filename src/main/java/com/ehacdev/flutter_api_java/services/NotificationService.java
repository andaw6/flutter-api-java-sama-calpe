package com.ehacdev.flutter_api_java.services;

import java.util.List;

import com.ehacdev.flutter_api_java.web.dto.response.NotificationResponseDTO;

public interface NotificationService {

    List<NotificationResponseDTO> getNotificationsCurrentUser();

    List<NotificationResponseDTO> getNotificationsCurrentUserNotRead();

    List<NotificationResponseDTO> getNotificationsCurrentUserRead();
}
