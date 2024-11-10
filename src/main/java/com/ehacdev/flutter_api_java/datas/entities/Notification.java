package com.ehacdev.flutter_api_java.datas.entities;

import com.ehacdev.flutter_api_java.datas.enums.NotificationStatus;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String message;

    @Enumerated(EnumType.STRING)
    private NotificationStatus isRead = NotificationStatus.UNREAD;
}
