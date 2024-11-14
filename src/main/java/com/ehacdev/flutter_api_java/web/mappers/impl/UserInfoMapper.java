package com.ehacdev.flutter_api_java.web.mappers.impl;

import java.util.Optional;

import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.web.dto.response.UserInfoDTO;

public class UserInfoMapper {
    public static UserInfoDTO toDto(User entity) {
        if(entity == null) return null;
        return new UserInfoDTO(
                entity.getId(),
                entity.getName(),
                Optional.ofNullable(entity.getEmail()),
                entity.getPhoneNumber(),
                entity.isActive(),
                entity.getRole());
    }

}
