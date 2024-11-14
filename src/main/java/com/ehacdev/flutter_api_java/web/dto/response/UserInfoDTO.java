package com.ehacdev.flutter_api_java.web.dto.response;

import java.util.Optional;
import java.util.UUID;

import com.ehacdev.flutter_api_java.datas.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoDTO {
    private UUID id;
    private String name;
    private Optional<String> email;
    private String phoneNumber;
    private boolean isActive;
    private UserRole role;
}
