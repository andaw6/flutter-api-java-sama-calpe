package com.ehacdev.flutter_api_java.datas.entities;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.ehacdev.flutter_api_java.datas.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"transactions", "received", "bills", "notifications", "contacts", "personalInfo"})
public class User extends BaseEntity implements UserDetails {
    private String name;
   
    @Column(unique = true, nullable = false)
    private String email;
   
    private String password;
   
    @Column(unique = true, nullable = false)
    private String phoneNumber;
   
    private boolean isActive = true;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.CLIENT;
    
    @OneToMany(mappedBy = "sender")
    private Set<Transaction> transactions;

    @OneToMany(mappedBy = "receiver")
    private Set<Transaction> received;

    @OneToMany(mappedBy = "user")
    private Set<Bill> bills;

    @OneToOne(mappedBy = "user")
    private Account account;

    @OneToMany(mappedBy = "user")
    private Set<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private Set<Contact> contacts;

    @OneToOne(mappedBy = "user")
    private PersonalInfo personalInfo;

    @Column(unique = true, nullable = false)
    private String cni;
   
    @Column(nullable = false)
    private String adresse;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return phoneNumber; // Utilisez l'email comme nom d'utilisateur
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // À adapter selon votre logique
    }

    @Override
    public boolean isAccountNonLocked() {
        return isActive; // L'utilisateur est non verrouillé s'il est actif
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // À adapter selon votre logique
    }

    @Override
    public boolean isEnabled() {
        return isActive; // L'utilisateur est activé s'il est actif
    }
}