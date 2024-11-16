package com.ehacdev.flutter_api_java.web.dto.response;

import java.util.Date;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyResponseDTO {
    private UUID id;
    private String name;
    private String type;
    private String icon;
    private Date createdAt;
    private Date updatedAt;
}
