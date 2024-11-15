package com.ehacdev.flutter_api_java.web.controllers.impl;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ehacdev.flutter_api_java.services.BillService;
import com.ehacdev.flutter_api_java.web.dto.response.BillResponseDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bills")
public class BillControllerImpl {

    private final BillService billService;
    
    @GetMapping("current")
    @PreAuthorize("hasRole('CLIENT') or hasRole('VENDOR')")  
    public ResponseEntity<List<BillResponseDTO>> getMethodName() {
        return ResponseEntity.ok(billService.getBillsCurrentUser());
    }
}
