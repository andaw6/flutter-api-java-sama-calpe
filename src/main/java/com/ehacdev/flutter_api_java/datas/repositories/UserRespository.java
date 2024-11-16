package com.ehacdev.flutter_api_java.datas.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ehacdev.flutter_api_java.datas.entities.User;


public interface UserRespository extends JpaRepository<User, UUID>{

    // Optional<User> findByPhoneNumber(String phoneNumber);

    List<User> findAll();

    Optional<User> findByPhoneNumber(String phoneNumber);

    // @EntityGraph(attributePaths = {"account"})
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.account WHERE u.phoneNumber = :id")
    User findUserWithRelations(@Param("id") String id);
}
