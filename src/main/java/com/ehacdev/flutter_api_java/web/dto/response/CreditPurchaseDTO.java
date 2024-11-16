package com.ehacdev.flutter_api_java.web.dto.response;



import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditPurchaseDTO {
    private String receiverName;
    private String receiverPhoneNumber;
    private String receiverEmail;
    private Date createdAt;
    private Date updatedAt;
}
