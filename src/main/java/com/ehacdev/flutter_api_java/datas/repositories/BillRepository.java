package com.ehacdev.flutter_api_java.datas.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ehacdev.flutter_api_java.datas.entities.Bill;

public interface BillRepository extends JpaRepository<Bill, UUID> {

    @Query("SELECT b FROM Bill b " +
            "WHERE b.user.phoneNumber = :id")
    List<Bill> getBillsByUserId(@Param("id") String id);
}
