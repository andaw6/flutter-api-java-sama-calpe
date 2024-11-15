package com.ehacdev.flutter_api_java.web.controllers.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehacdev.flutter_api_java.services.UserService;
import com.ehacdev.flutter_api_java.web.dto.response.UserInfoDTO;
import com.ehacdev.flutter_api_java.web.dto.response.UserResponseDTO;
import com.ehacdev.flutter_api_java.web.mappers.impl.UserInfoMapper;
import com.ehacdev.flutter_api_java.web.mappers.impl.UserResponseMapper;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserControllerImpl {

    private final UserService userService;

    @GetMapping("current")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')")
    public ResponseEntity<UserResponseDTO> getCurrentUser() {
        return ResponseEntity.ok(UserResponseMapper.toDto(userService.getCurrentUser()));
    }

    @GetMapping("phone/{phoneNumber}")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')")
    public ResponseEntity<UserInfoDTO> getByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(UserInfoMapper.toDto(userService.getUserByPhoneNumber(phoneNumber)));
    }

}
