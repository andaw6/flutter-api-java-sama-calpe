package com.ehacdev.flutter_api_java.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ehacdev.flutter_api_java.datas.entities.Notification;
import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.datas.enums.NotificationStatus;
import com.ehacdev.flutter_api_java.datas.repositories.NotificationRepository;
import com.ehacdev.flutter_api_java.security.AuthService;
import com.ehacdev.flutter_api_java.services.NotificationService;
import com.ehacdev.flutter_api_java.services.UserService;
import com.ehacdev.flutter_api_java.utils.Utils;
import com.ehacdev.flutter_api_java.web.dto.response.NotificationResponseDTO;
import com.ehacdev.flutter_api_java.web.mappers.impl.NotificationResponseMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private final AuthService authService;
    private final NotificationRepository notificationRepository;
    private final UserService userService;

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

    @Override
    public Boolean markReadNotification(String id) {
        Notification notification = notificationRepository.findById(Utils.convertUUID(id)).orElseThrow();
        notification.setIsRead(NotificationStatus.READ);
        notification.setReadAt(new Date());
        notificationRepository.save(notification);
        return true;
    }

    @Override
    public Notification createNotification(String content) {
        Notification notification = create(userService.getCurrentUser(), content);
        notificationRepository.save(notification);
        return notification;
    }

    @Override
    public Notification createNotification(User user, String content) {
        Notification notification = create(user, content);
        notificationRepository.save(notification);
        return notification;
    }

    private Notification create(User user, String content) {
        return Notification
                .builder()
                .user(user)
                .message(content)
                .isRead(NotificationStatus.UNREAD)
                .build();
    }
}
