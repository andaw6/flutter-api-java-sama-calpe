package com.ehacdev.flutter_api_java.datas.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ehacdev.flutter_api_java.datas.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    @Query("SELECT t FROM Transaction t " +
            "LEFT JOIN FETCH t.creditPurchase " +
            "LEFT JOIN FETCH t.sender " +
            "LEFT JOIN FETCH t.receiver " +
            "WHERE t.sender.phoneNumber = :id OR t.receiver.phoneNumber = :id")
    List<Transaction> getTransactionsByUserId(@Param("id") String id);
}
