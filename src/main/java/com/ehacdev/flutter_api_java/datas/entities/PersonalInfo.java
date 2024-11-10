package com.ehacdev.flutter_api_java.datas.entities;

import java.time.LocalDateTime;

import com.ehacdev.flutter_api_java.datas.enums.PersonalInfoStatus;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personal_infos")
public class PersonalInfo extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "userId", nullable = false, unique = true)
    private User user;

    private String documentType;
 
    private String idCardFrontPhoto;
 
    private String idCardBackPhoto;

    @Enumerated(EnumType.STRING)
    private PersonalInfoStatus verificationStatus;

    private LocalDateTime verifiedAt;
 
    private String verificationMethod;
 
    private String rejectionReason;
}
