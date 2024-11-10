package com.ehacdev.flutter_api_java.datas.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ehacdev.flutter_api_java.datas.entities.User;


public interface UserRespository extends JpaRepository<User, UUID>{

    Optional<User> findByPhoneNumber(String phoneNumber);
}
