package com.ehacdev.flutter_api_java.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthService {
    UserDetails userAuth();
}
