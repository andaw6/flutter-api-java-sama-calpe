package com.ehacdev.flutter_api_java.datas.seeders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ehacdev.flutter_api_java.datas.entities.Bill;
import com.ehacdev.flutter_api_java.datas.entities.Company;
import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.datas.enums.BillStatus;
import com.ehacdev.flutter_api_java.datas.repositories.BillRepository;
import com.ehacdev.flutter_api_java.datas.repositories.CompanyRepository;
import com.ehacdev.flutter_api_java.datas.repositories.UserRespository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BillSeeder extends BaseSeeder {

    private final BillRepository billRepository;
    private final CompanyRepository companyRepository;
    private final UserRespository userRespository;

    @Override
    @Transactional
    public void seed() {
        List<Bill> billExample = new ArrayList<>();
        User user1 = userRespository.findByPhoneNumber("778133537").orElseThrow();
        User user2 = userRespository.findByPhoneNumber("785910767").orElseThrow();
        Company company = companyRepository.findByName("SDE");
        Company company2 = companyRepository.findByName("UCAD");

        billExample.add(new Bill(user1, company, 100, "XOR", BillStatus.PAID));
        billExample.add(new Bill(user1, company, 150, "XOR", BillStatus.PAID));
        billExample.add(new Bill(user2, company2, 300, "XOR", BillStatus.PAID));
        billExample.add(new Bill(user1, company, 1000, "XOR", BillStatus.PAID));
        billExample.add(new Bill(user2, company, 1500, "XOR", BillStatus.PAID));

        billRepository.saveAll(billExample);
    }
}
