package com.ehacdev.flutter_api_java.services.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.datas.enums.UserRole;
import com.ehacdev.flutter_api_java.datas.repositories.UserRespository;
import com.ehacdev.flutter_api_java.security.AuthService;
import com.ehacdev.flutter_api_java.services.UserService;
import com.ehacdev.flutter_api_java.web.dto.request.ClientRegisterRequestDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRespository userRespository;
    private final AuthService authService;

    public User createClient(ClientRegisterRequestDTO request) {
        var user = User
                .builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phoneNumber(request.getPhone())
                .role(UserRole.CLIENT)
                .adresse(request.getAdresse())
                .cni(request.getCni())  
                .isActive(true)
                .build();
        return userRespository.save(user);
    }

    public User getUserByPhoneNumber(String phoneNumer) {
        return userRespository
                .findByPhoneNumber(phoneNumer)
                .orElseThrow();
    }

    public User getCurrentUser(){
        UserDetails userAuth = authService.userAuth();
        return userRespository.findUserWithRelations(userAuth.getUsername()); 
    }
}
