package com.ehacdev.flutter_api_java.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.UUID;

import com.ehacdev.flutter_api_java.datas.enums.UserRole;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private String phoneNumber;
    private boolean isActive;   
    private UserRole role; 
    // private Set<TransactionResponseDTO> transactions;
    // private Set<TransactionResponseDTO> receivedTransactions;
    // private Set<BillResponseDTO> bills;
    private AccountResponseDTO account;
    // private Set<ContactResponseDTO> contacts;
}