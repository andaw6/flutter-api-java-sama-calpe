package com.ehacdev.flutter_api_java.web.controllers.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehacdev.flutter_api_java.services.NotificationService;
import com.ehacdev.flutter_api_java.web.dto.response.NotificationResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notifications")
public class NotificationControllerImpl {
    private final NotificationService notificationService;

    @GetMapping("current")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')")
    public ResponseEntity<List<NotificationResponseDTO>> getAllNotifications() {
        List<NotificationResponseDTO> notifications = notificationService.getNotificationsCurrentUser();
        return ResponseEntity.ok(notifications);
    }

    @GetMapping("/current/unread")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')")
    public ResponseEntity<List<NotificationResponseDTO>> getUnreadNotifications() {
        List<NotificationResponseDTO> unreadNotifications = notificationService.getNotificationsCurrentUserNotRead();
        return ResponseEntity.ok(unreadNotifications);
    }

    @GetMapping("/current/read")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')")
    public ResponseEntity<List<NotificationResponseDTO>> getReadNotifications() {
        List<NotificationResponseDTO> readNotifications = notificationService.getNotificationsCurrentUserRead();
        return ResponseEntity.ok(readNotifications);
    }
}