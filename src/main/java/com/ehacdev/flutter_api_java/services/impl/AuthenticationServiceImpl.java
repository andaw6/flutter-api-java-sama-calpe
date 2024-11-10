package com.ehacdev.flutter_api_java.services.impl;

import java.sql.Date;
import java.util.HashMap;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.ehacdev.flutter_api_java.datas.entities.BlacklistedToken;
import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.datas.repositories.BlacklistedTokenRepository;
import com.ehacdev.flutter_api_java.security.JwtService;
import com.ehacdev.flutter_api_java.services.AccountService;
import com.ehacdev.flutter_api_java.services.AuthenticationService;
import com.ehacdev.flutter_api_java.services.UserService;
import com.ehacdev.flutter_api_java.web.dto.request.AuthenticationRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.request.ClientRegisterRequestDTO;
import com.ehacdev.flutter_api_java.web.dto.response.AuthenticationResponseDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.var;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final AccountService accountService;
    private final BlacklistedTokenRepository blacklistedTokenRepository;

    @Transactional
    public AuthenticationResponseDTO clientRegister(ClientRegisterRequestDTO request) {

        User userRegister = userService.createClient(request);
        accountService.createAccountForUser(userRegister);
        var jwtToken = jwtService.generateToken(getDataForToken(userRegister), userRegister);
        return AuthenticationResponseDTO
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhone(),
                        request.getPassword()));
        User user = userService.getUserByPhoneNumber(request.getPhone());
        var jwtToken = jwtService.generateToken(getDataForToken(user), user);
        return AuthenticationResponseDTO
                .builder()
                .token(jwtToken)
                .build();
    }

    @Transactional
    public AuthenticationResponseDTO logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            blacklistedTokenRepository.save(
                    BlacklistedToken.builder()
                            .token(token)
                            .expirationDate(jwtService.extractExpiration(token))
                            .build());
        }

        return AuthenticationResponseDTO
                .builder()
                .token(null) // On renvoie null car l'utilisateur se déconnecte
                .build();
    }

    private HashMap<String, Object> getDataForToken(User user) {
        return new HashMap<String, Object>() {
            {
                put("userId", user.getId());
                put("phoneNumber", user.getPhoneNumber());
                put("role", user.getRole());
            }
        };
    }
}
