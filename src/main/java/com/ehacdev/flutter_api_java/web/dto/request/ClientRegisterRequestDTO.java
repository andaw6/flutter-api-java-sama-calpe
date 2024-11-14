package com.ehacdev.flutter_api_java.web.dto.request;

import jakarta.validation.constraints.Email;
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
public class ClientRegisterRequestDTO {

    @NotBlank(message = "Le nom est obligatoire.")
    private String name;

    @NotBlank(message = "L'email est obligatoire.")
    @Email(message = "Veuillez fournir une adresse email valide.")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire.")
    @Pattern(regexp = "^\\d{4}$", message = "Le mot de passe doit être un code de 4 chiffres.")
    private String password;

    @NotBlank(message = "Le numéro de téléphone est obligatoire.")
    @Pattern(
        regexp = "^(221)?(7[0678]\\d{7})$",
        message = "Le numéro de téléphone doit être un numéro sénégalais valide."
    )
    private String phone;

    // Validation pour la CNI (format : SN suivi de 9 chiffres)
    @NotBlank(message = "Le CNI est obligatoire.")
    @Pattern(regexp = "^SN\\d{9}$", message = "Le CNI doit suivre le format SN suivi de 9 chiffres.")
    private String cni;

    // Validation de l'adresse (non vide et taille entre 10 et 255 caractères)
    @NotBlank(message = "L'adresse est obligatoire.")
    @Size(min = 10, max = 255, message = "L'adresse doit comporter entre 10 et 255 caractères.")
    private String adresse;
}
