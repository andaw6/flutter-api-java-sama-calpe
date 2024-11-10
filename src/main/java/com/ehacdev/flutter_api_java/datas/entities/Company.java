package com.ehacdev.flutter_api_java.datas.entities;

import java.util.List;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "companies")
public class Company extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String name;
    
    private String type;
    
    private String icon;

    @OneToMany(mappedBy = "company")
    private List<Bill> bills;
}
