package com.ehacdev.flutter_api_java.datas.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ehacdev.flutter_api_java.datas.entities.Notification;

public interface NotificationRepository extends JpaRepository<Notification, UUID> {

    @Query("SELECT n FROM Notification n " +
            "WHERE n.user.phoneNumber = :id")
    List<Notification> getNotificationsByUserId(@Param("id") String id);
}
