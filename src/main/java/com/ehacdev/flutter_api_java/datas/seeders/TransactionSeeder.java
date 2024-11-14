package com.ehacdev.flutter_api_java.datas.seeders;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ehacdev.flutter_api_java.datas.entities.CreditPurchase;
import com.ehacdev.flutter_api_java.datas.entities.Transaction;
import com.ehacdev.flutter_api_java.datas.entities.User;
import com.ehacdev.flutter_api_java.datas.enums.TransactionStatus;
import com.ehacdev.flutter_api_java.datas.enums.TransactionType;
import com.ehacdev.flutter_api_java.datas.repositories.CreditPurchaseRepository;
import com.ehacdev.flutter_api_java.datas.repositories.TransactionRepository;
import com.ehacdev.flutter_api_java.datas.repositories.UserRespository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TransactionSeeder extends BaseSeeder {
    private final UserRespository userRespository;
    private final TransactionRepository transactionRepository;
    private final CreditPurchaseRepository creditPurchaseRepository;

    @Override
    @Transactional
    public void seed() {

        User user1 = userRespository.findByPhoneNumber("778133537").orElseThrow();
        User user2 = userRespository.findByPhoneNumber("785910767").orElseThrow();

        List<Transaction> transactionExample = new ArrayList<>();

        transactionExample.add(
                Transaction.builder()
                        .amount(100)
                        .sender(user2)
                        .receiver(user1)
                        .currency("XOR")
                        .feeAmount(10)
                        .transactionType(TransactionType.TRANSFER)
                        .status(TransactionStatus.COMPLETED)
                        .build());
        transactionExample.add(
                Transaction.builder()
                        .amount(1300)
                        .sender(user1)
                        .receiver(user2)
                        .currency("XOR")
                        .feeAmount(10)
                        .transactionType(TransactionType.TRANSFER)
                        .status(TransactionStatus.COMPLETED)
                        .build());
        transactionExample.add(
                Transaction.builder()
                        .amount(100)
                        .sender(user2)
                        .receiver(user1)
                        .currency("XOR")
                        .feeAmount(10)
                        .transactionType(TransactionType.TRANSFER)
                        .status(TransactionStatus.CANCELLED)
                        .build());
        transactionExample.add(
                Transaction.builder()
                        .amount(100)
                        .sender(user2)
                        .receiver(user1)
                        .currency("XOR")
                        .feeAmount(10)
                        .transactionType(TransactionType.TRANSFER)
                        .status(TransactionStatus.COMPLETED)
                        .build());
        Transaction transaction = Transaction.builder()
                .amount(100)
                .sender(user1)
                .currency("XOR")
                .feeAmount(0)
                .transactionType(TransactionType.PURCHASE)
                .status(TransactionStatus.COMPLETED)
                .build();

        CreditPurchase creditPurchase = CreditPurchase.builder()
                .transaction(transaction)
                .receiverName("Makhtar tine")
                .receiverPhoneNumber("770643381")
                .receiverEmail("receiver@example.com")
                .build();

        transactionRepository.save(transaction);
        creditPurchaseRepository.save(creditPurchase);
        transactionRepository.saveAll(transactionExample);
    }
}