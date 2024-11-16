package com.ehacdev.flutter_api_java.web.dto.response;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactResponseDTO {
    private UUID id;
    private UUID userId;
    private String name;
    private Optional<String> email;
    private String phoneNumber;
    private boolean favorite;
    private Date createdAt;
    private Date updatedAt;
}
