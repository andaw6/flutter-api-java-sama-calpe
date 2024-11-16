package com.ehacdev.flutter_api_java.web.mappers.impl;

import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.web.dto.response.UserResponseDTO;

public class UserResponseMapper {

    public static UserResponseDTO toDto(User entity) {
        return new UserResponseDTO(
            entity.getId(),
            entity.getName(),
            entity.getEmail(),
            entity.getPhoneNumber(),
            entity.isActive(),
            entity.getRole(),
            AccountResponseMapper.toDto(entity.getAccount()),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
