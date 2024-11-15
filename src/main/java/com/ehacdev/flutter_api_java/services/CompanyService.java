package com.ehacdev.flutter_api_java.services;

import java.util.List;

import com.ehacdev.flutter_api_java.web.dto.response.CompanyResponseDTO;

public interface CompanyService {

    List<CompanyResponseDTO> getCompanies();

}
