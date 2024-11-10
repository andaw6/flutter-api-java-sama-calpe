package com.ehacdev.flutter_api_java.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientRegisterRequestDTO {

    private String name;
    
    private String email;
    
    private String password;
    
    private String phone;
}
