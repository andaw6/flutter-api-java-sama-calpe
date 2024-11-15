package com.ehacdev.flutter_api_java.web.controllers.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehacdev.flutter_api_java.services.ContactService;
import com.ehacdev.flutter_api_java.web.dto.response.ContactResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contacts")
public class ContactControllerImpl {
    private final ContactService contactService;
    
    @GetMapping("current")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')")  
    public ResponseEntity<List<ContactResponseDTO>> getMethodName() {
        return ResponseEntity.ok(contactService.getContactsCurrentUser());
    }
}
