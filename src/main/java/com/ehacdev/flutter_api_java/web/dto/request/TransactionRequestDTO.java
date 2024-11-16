package com.ehacdev.flutter_api_java.web.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class TransactionRequestDTO {

    @NotNull(message = "Le montant est obligatoire.")
    @DecimalMin(value = "5.0", message = "Le montant doit être supérieur ou égal à 5.")
    private Double amount;

    @NotBlank(message = "Le numéro de téléphone est obligatoire.")
    @Pattern(
        regexp = "^(?:\\+221)?(77|76|75|78|70)\\d{7}$",
        message = "Le numéro de téléphone du destinataire doit être un numéro sénégalais valide."
    )
    private String phone;

    @DecimalMax(value = "99999", message = "Les frais dépassent la limite autorisée.")
    private Double feeAmount = 0.0;

    private String currency = "XOR";
}
