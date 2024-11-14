package com.ehacdev.flutter_api_java.security.impl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ehacdev.flutter_api_java.security.AuthService;

@Service
public class AuthServiceImpl implements AuthService{
    
    public UserDetails userAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) principal;
            return userDetails;
        } else {
            return null;
        }
    }
}
