package com.ehacdev.flutter_api_java.datas.entities;



import java.util.Date;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "black_list_tokens")  
public class BlacklistedToken extends BaseEntity{
    
    @Column(unique = true, nullable = false)
    private String token;

    private Date expirationDate;
}
