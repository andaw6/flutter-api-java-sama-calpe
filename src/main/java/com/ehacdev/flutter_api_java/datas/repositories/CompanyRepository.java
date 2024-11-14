package com.ehacdev.flutter_api_java.datas.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ehacdev.flutter_api_java.datas.entities.Company;


public interface CompanyRepository extends JpaRepository<Company, UUID> {
    Company findByName(String name);
}
