package com.ehacdev.flutter_api_java.web.controllers.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehacdev.flutter_api_java.services.AuthenticationService;
import com.ehacdev.flutter_api_java.web.controllers.AuthController;
import com.ehacdev.flutter_api_java.web.dto.request.AuthenticationRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.request.ClientRegisterRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.response.AuthenticationResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthControllerImpl implements AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/client/register")
    public ResponseEntity<AuthenticationResponseDTO> clientRegister(@Valid @RequestBody ClientRegisterRequestDTO entity) {
        return ResponseEntity.ok(authenticationService.clientRegister(entity));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@Valid @RequestBody AuthenticationRequestDTO entity) {
        System.out.println("\n\n\n"+entity);
        return ResponseEntity.ok(authenticationService.login(entity));
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthenticationResponseDTO> logout(HttpServletRequest request) {
        return ResponseEntity.ok(authenticationService.logout(request));
    }

}
