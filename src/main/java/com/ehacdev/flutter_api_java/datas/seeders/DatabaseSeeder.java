package com.ehacdev.flutter_api_java.datas.seeders;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {
    private final CompanySeeder companySeeder;
    private final BillSeeder billSeeder;
    private final TransactionSeeder transactionSeeder;

    @Override
    public void run(String... args) {
        // transactionSeeder.seed();
        // companySeeder.seed();
        // billSeeder.seed();
    }
}
