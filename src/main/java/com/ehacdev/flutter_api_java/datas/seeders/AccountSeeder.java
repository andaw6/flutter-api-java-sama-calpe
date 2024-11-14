package com.ehacdev.flutter_api_java.datas.seeders;

import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountSeeder  extends BaseSeeder {

    @Override
    @Transactional
    public void seed() {
    }
}