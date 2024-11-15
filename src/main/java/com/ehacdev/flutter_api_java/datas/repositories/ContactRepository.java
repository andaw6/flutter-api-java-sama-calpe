package com.ehacdev.flutter_api_java.datas.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ehacdev.flutter_api_java.datas.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, UUID>{

      @Query("SELECT c FROM Contact c " +
            "WHERE c.user.phoneNumber = :id")
    List<Contact> getContactsByUserId(@Param("id") String id);
}
