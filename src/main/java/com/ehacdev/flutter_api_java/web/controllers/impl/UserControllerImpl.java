package com.ehacdev.flutter_api_java.web.controllers.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehacdev.flutter_api_java.services.UserService;
import com.ehacdev.flutter_api_java.web.dto.response.UserResponseDTO;
import com.ehacdev.flutter_api_java.web.mappers.impl.UserResponseMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserControllerImpl {

    private final UserService userService;

    @GetMapping("current")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')")  
    public ResponseEntity<UserResponseDTO> getMethodName() {
        return ResponseEntity.ok(UserResponseMapper.toDto(userService.getCurrentUser()));
    }
    

}
