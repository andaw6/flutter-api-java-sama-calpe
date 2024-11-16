package com.ehacdev.flutter_api_java.services.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ehacdev.flutter_api_java.datas.entities.CreditPurchase;
import com.ehacdev.flutter_api_java.datas.entities.Transaction;
import com.ehacdev.flutter_api_java.datas.repositories.CreditPurchaseRepository;
import com.ehacdev.flutter_api_java.services.CreditPurchaseService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditPurchaseServiceImpl implements CreditPurchaseService {
    private final CreditPurchaseRepository creditPurchaseRepository;

    @Override
    public CreditPurchase create(
            Transaction transaction,
            String receiverName,
            String receiverPhoneNumber,
            Optional<String> receiverEmail) {
        return CreditPurchase.builder()
                .transaction(transaction)
                .receiverName(receiverName)
                .receiverPhoneNumber(receiverPhoneNumber)
                .receiverEmail(receiverEmail.orElse(null))
                .build();
    }

    public CreditPurchase createCreditPurchase(
        Transaction transaction,
        String receiverName,
        String receiverPhoneNumber,
        Optional<String> receiverEmail){
            CreditPurchase credit = this.create(transaction, receiverName, receiverPhoneNumber, receiverEmail);
            return this.creditPurchaseRepository.save(credit);
        }

    public CreditPurchase save(CreditPurchase credit){
        return this.creditPurchaseRepository.save(credit);
    }

}
