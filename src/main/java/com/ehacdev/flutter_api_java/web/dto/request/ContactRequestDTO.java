package com.ehacdev.flutter_api_java.web.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ContactRequestDTO {

    @NotBlank(message = "Le nom est requis.")
    @Size(max = 255, message = "Le nom ne peut pas dépasser 255 caractères.")
    private String name;

    @NotBlank(message = "Le numéro de téléphone est requis.")
    @Pattern(
        regexp = "^(?:\\+221)?(77|76|75|78|70)\\d{7}$",
        message = "Le numéro de téléphone doit être un numéro sénégalais valide."
    )
    private String phone;
}
