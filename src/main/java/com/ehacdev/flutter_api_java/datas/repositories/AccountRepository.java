package com.ehacdev.flutter_api_java.datas.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ehacdev.flutter_api_java.datas.entities.Account;

public interface AccountRepository extends JpaRepository<Account, UUID>{

    @Query("SELECT a FROM Account a WHERE a.user.phoneNumber = :id")
    Optional<Account> findByUserPhoneNumber(@Param("id") String phoneNumber);
    
}
