package com.ehacdev.flutter_api_java.services;

import java.util.Optional;

import com.ehacdev.flutter_api_java.datas.entities.CreditPurchase;
import com.ehacdev.flutter_api_java.datas.entities.Transaction;

public interface CreditPurchaseService {

    CreditPurchase create(
        Transaction transaction, 
        String receiverName, 
        String receiverPhoneNumber, 
        Optional<String> receiverEmail
    );

    CreditPurchase createCreditPurchase(
        Transaction transaction,
        String receiverName,
        String receiverPhoneNumber,
        Optional<String> receiverEmail);

    CreditPurchase save(CreditPurchase credit);
}
