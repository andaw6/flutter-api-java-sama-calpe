package com.ehacdev.flutter_api_java.web.controllers.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehacdev.flutter_api_java.services.CompanyService;
import com.ehacdev.flutter_api_java.web.dto.response.CompanyResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companies")
public class CompanyControllerImpl {

    private final CompanyService companyService;
    
    @GetMapping("all")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')")  
    public ResponseEntity<List<CompanyResponseDTO>> getMethodName() {
        return ResponseEntity.ok(companyService.getCompanies());
    }
}
