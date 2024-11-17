package com.ehacdev.flutter_api_java.services;

import java.util.List;

import com.ehacdev.flutter_api_java.datas.entities.Notification;
import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.web.dto.response.NotificationResponseDTO;

public interface NotificationService {

    List<NotificationResponseDTO> getNotificationsCurrentUser();

    List<NotificationResponseDTO> getNotificationsCurrentUserNotRead();

    List<NotificationResponseDTO> getNotificationsCurrentUserRead();

    Boolean markReadNotification(String id);

    Notification createNotification(String content);

    Notification createNotification(User user, String content);
}
