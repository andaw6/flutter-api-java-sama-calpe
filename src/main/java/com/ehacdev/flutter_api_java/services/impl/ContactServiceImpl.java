package com.ehacdev.flutter_api_java.services.impl;

import com.ehacdev.flutter_api_java.datas.entities.Contact;
import com.ehacdev.flutter_api_java.datas.repositories.ContactRepository;
import com.ehacdev.flutter_api_java.security.AuthService;
import com.ehacdev.flutter_api_java.services.ContactService;
import com.ehacdev.flutter_api_java.web.dto.response.ContactResponseDTO;
import com.ehacdev.flutter_api_java.web.mappers.impl.ContactResponseMapper;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService{
    private final AuthService authService;
    private final ContactRepository contactRepository;

    public List<ContactResponseDTO> getContactsCurrentUser(){
          UserDetails userAuth = authService.userAuth();
        List<Contact> contacts = contactRepository.getContactsByUserId(userAuth.getUsername());
        return contacts.stream()
                .map(ContactResponseMapper::toDto).toList();
    }
}
