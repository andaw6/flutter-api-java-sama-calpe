package com.ehacdev.flutter_api_java.datas.entities;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({"bills"})
@Table(name = "companies")
public class Company extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String name;
    
    private String type;
    
    private String icon;

    @OneToMany(mappedBy = "company")
    private Set<Bill> bills;
}
