package com.ehacdev.flutter_api_java.datas.seeders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ehacdev.flutter_api_java.datas.entities.Contact;
import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.datas.repositories.ContactRepository;
import com.ehacdev.flutter_api_java.datas.repositories.UserRespository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ContactSeeder extends BaseSeeder {

    private final UserRespository userRespository;
    private final ContactRepository contactRepository;

    @Override
    @Transactional
    public void seed() {
        
        User user1 = userRespository.findByPhoneNumber("778133537").orElseThrow();
        User user2 = userRespository.findByPhoneNumber("785910767").orElseThrow();

        List<Contact> contactExample = new ArrayList<>();

        contactExample.add(new Contact(user1, user2.getName(), user2.getPhoneNumber(), user2.getEmail(), true));
        contactExample.add(new Contact(user2, user1.getName(), user1.getPhoneNumber(), user1.getEmail(), true));

        contactRepository.saveAll(contactExample);  
    }
}
