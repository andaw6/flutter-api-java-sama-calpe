package com.ehacdev.flutter_api_java.web.mappers.impl;

import java.util.Optional;

import com.ehacdev.flutter_api_java.datas.entities.Contact;
import com.ehacdev.flutter_api_java.web.dto.response.ContactResponseDTO;

public class ContactResponseMapper {

    public static ContactResponseDTO toDto(Contact entity) {
        return new ContactResponseDTO(
            entity.getId(),
            entity.getName(),
            Optional.ofNullable(entity.getEmail()),
            entity.getPhoneNumber(),
            entity.isFavorite()
        );
    }

}
