package com.ehacdev.flutter_api_java.datas.entities;

import java.util.Date;

import com.ehacdev.flutter_api_java.datas.enums.NotificationStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "notifications")
@JsonIgnoreProperties({"user"})
public class Notification extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String message;

    @Builder.Default    
    @Enumerated(EnumType.STRING)
    private NotificationStatus isRead = NotificationStatus.UNREAD;

    @Builder.Default
    @Temporal(TemporalType.TIMESTAMP)
    protected Date readAt = null;

}
