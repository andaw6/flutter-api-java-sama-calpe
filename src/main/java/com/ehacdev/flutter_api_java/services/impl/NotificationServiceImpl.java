package com.ehacdev.flutter_api_java.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ehacdev.flutter_api_java.datas.enums.NotificationStatus;
import com.ehacdev.flutter_api_java.datas.repositories.NotificationRepository;
import com.ehacdev.flutter_api_java.security.AuthService;
import com.ehacdev.flutter_api_java.services.NotificationService;
import com.ehacdev.flutter_api_java.web.dto.response.NotificationResponseDTO;
import com.ehacdev.flutter_api_java.web.mappers.impl.NotificationResponseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final AuthService authService;
    private final NotificationRepository notificationRepository;

     @Override
    public List<NotificationResponseDTO> getNotificationsCurrentUser() {
        String currentUserPhone = authService.userAuth().getUsername();
        return notificationRepository.getNotificationsByUserId(currentUserPhone)
                .stream()
                .map(NotificationResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsCurrentUserNotRead() {
        String currentUserPhone = authService.userAuth().getUsername();
        return notificationRepository.getNotificationsByUserId(currentUserPhone)
                .stream()
                .filter(notification -> notification.getIsRead() == NotificationStatus.UNREAD)
                .map(NotificationResponseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<NotificationResponseDTO> getNotificationsCurrentUserRead() {
        String currentUserPhone = authService.userAuth().getUsername();
        return notificationRepository.getNotificationsByUserId(currentUserPhone)
                .stream()
                .filter(notification -> notification.getIsRead() == NotificationStatus.READ)
                .map(NotificationResponseMapper::toDto)
                .collect(Collectors.toList());
    }

}
