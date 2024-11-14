package com.ehacdev.flutter_api_java.datas.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
@JsonIgnoreProperties({"user"})
public class Contact extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String name;
    
    private String phoneNumber;
    
    private String email;
    
    @Builder.Default
    private boolean favorite = false;
}
