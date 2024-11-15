package com.ehacdev.flutter_api_java.services.impl;

import com.ehacdev.flutter_api_java.datas.repositories.CompanyRepository;
import com.ehacdev.flutter_api_java.services.CompanyService;
import com.ehacdev.flutter_api_java.web.dto.response.CompanyResponseDTO;
import com.ehacdev.flutter_api_java.web.mappers.impl.CompanyResponseMapper;

import java.util.List;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public List<CompanyResponseDTO> getCompanies() {
        return companyRepository.findAll()
                                .stream()
                                .map(CompanyResponseMapper::toDto)
                                .toList();
    }
}
