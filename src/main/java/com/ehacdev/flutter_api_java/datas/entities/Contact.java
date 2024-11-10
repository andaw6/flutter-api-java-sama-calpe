package com.ehacdev.flutter_api_java.datas.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contacts")
public class Contact extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String name;
    
    private String phoneNumber;
    
    private String email;
    
    private boolean favorite = false;
}
