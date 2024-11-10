package com.ehacdev.flutter_api_java.datas.entities;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.ehacdev.flutter_api_java.datas.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
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
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> received;

    @OneToMany(mappedBy = "user")
    private List<Bill> bills;

    @OneToOne(mappedBy = "user")
    private Account account;

    @OneToMany(mappedBy = "user")
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user")
    private List<Contact> contacts;

    @OneToOne(mappedBy = "user")
    private PersonalInfo personalInfo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retourne les rôles de l'utilisateur sous forme de GrantedAuthority
        return List.of(new SimpleGrantedAuthority(role.name()));
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