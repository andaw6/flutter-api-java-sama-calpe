package com.ehacdev.flutter_api_java.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO {

 @NotBlank(message = "Le numéro de téléphone est obligatoire.")
    @Pattern(
        regexp = "^(221)?(7[0678]\\d{7})$",
        message = "Le numéro de téléphone doit être un numéro sénégalais valide."
    )
    private String phone;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Size(min = 8, message = "Le mot de passe doit contenir au moins 8 caractères.")
    private String password;

}
