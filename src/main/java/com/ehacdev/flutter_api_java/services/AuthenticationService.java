package com.ehacdev.flutter_api_java.services;


import com.ehacdev.flutter_api_java.web.dto.request.AuthenticationRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.request.ClientRegisterRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.response.AuthenticationResponseDTO;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthenticationService {

    AuthenticationResponseDTO clientRegister(ClientRegisterRequestDTO request);

    AuthenticationResponseDTO login(AuthenticationRequestDTO request);

    AuthenticationResponseDTO logout(HttpServletRequest request);

   
}
