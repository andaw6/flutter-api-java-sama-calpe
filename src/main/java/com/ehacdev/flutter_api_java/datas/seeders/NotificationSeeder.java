package com.ehacdev.flutter_api_java.datas.seeders;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ehacdev.flutter_api_java.datas.entities.Notification;
import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.datas.enums.NotificationStatus;
import com.ehacdev.flutter_api_java.datas.repositories.NotificationRepository;
import com.ehacdev.flutter_api_java.datas.repositories.UserRespository;
    
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component  
@RequiredArgsConstructor
public class NotificationSeeder extends BaseSeeder{
    private final UserRespository userRespository;
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public void seed() {
        
        User user1 = userRespository.findByPhoneNumber("778133537").orElseThrow();
        User user2 = userRespository.findByPhoneNumber("785910767").orElseThrow();

        List<Notification> notificationExample = new ArrayList<>();
        notificationExample.add(new Notification(user1, "vous avez reçu 100F de Diary", NotificationStatus.READ, new Date()));
        notificationExample.add(new Notification(user2, "vous avez reçu 1000F de Andaw", NotificationStatus.READ, new Date()));
        notificationExample.add(new Notification(user1, "vous avez reçu 100F de Diary", NotificationStatus.UNREAD, null));
        notificationExample.add(new Notification(user2, "vous avez reçu 1000F de Andaw", NotificationStatus.READ, new Date()));
        notificationExample.add(new Notification(user1, "vous avez reçu 100F de Diary", NotificationStatus.UNREAD, null));
        notificationExample.add(new Notification(user2, "vous avez reçu 1000F de Andaw", NotificationStatus.READ, new Date()));
        notificationRepository.saveAll(notificationExample);  
    }
}
