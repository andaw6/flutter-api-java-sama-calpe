package com.ehacdev.flutter_api_java.web.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BillRequestDTO {

    @NotNull(message = "Le montant est requis.")
    @DecimalMin(value = "0.0", message = "Le montant doit être supérieur ou égal à 0.")
    private Double amount;

    @NotBlank(message = "L'ID de l'entreprise est requis.")
    @Pattern(
        regexp = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$",
        message = "L'ID de l'entreprise doit être un UUID valide."
    )
    private String companyId;
}
