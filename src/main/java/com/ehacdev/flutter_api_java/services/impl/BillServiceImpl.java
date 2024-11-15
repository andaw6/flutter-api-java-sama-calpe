package com.ehacdev.flutter_api_java.services.impl;

import com.ehacdev.flutter_api_java.datas.entities.Bill;
import com.ehacdev.flutter_api_java.datas.repositories.BillRepository;
import com.ehacdev.flutter_api_java.security.AuthService;
import com.ehacdev.flutter_api_java.services.BillService;
import com.ehacdev.flutter_api_java.web.dto.response.BillResponseDTO;
import com.ehacdev.flutter_api_java.web.mappers.impl.BillResponseMapper;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BillServiceImpl implements BillService {
    private final AuthService authService;
    private final BillRepository billRepository;

    public List<BillResponseDTO> getBillsCurrentUser() {
        UserDetails userAuth = authService.userAuth();
        List<Bill> bills = billRepository.getBillsByUserId(userAuth.getUsername());
        return bills.stream()
                .map(BillResponseMapper::toDto).toList();
    }
}
    