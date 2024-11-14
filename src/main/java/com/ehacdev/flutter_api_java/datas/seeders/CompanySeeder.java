package com.ehacdev.flutter_api_java.datas.seeders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ehacdev.flutter_api_java.datas.entities.Company;
import com.ehacdev.flutter_api_java.datas.repositories.CompanyRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CompanySeeder extends BaseSeeder{
    private final CompanyRepository companyRepository;


    @Override
    @Transactional
    public void seed(){
        List<Company> companyExample = new ArrayList<>();
             
        companyExample.add(new Company("Senelec", "ELECTRICITY", "https://www.senelec.sn/assets/uploads/media-uploader/logo1637145113.png", new HashSet<>()));
        companyExample.add(new Company("SDE", "WATER", "https://upload.wikimedia.org/wikipedia/fr/e/ed/Logo_SDE_S%C3%A9n%C3%A9gal.jpg", new HashSet<>()));
        companyExample.add(new Company("Sonatel", "INTERNET", "https://orange.africa-newsroom.com/files/large/3a46a62fb05c98e", new HashSet<>()));
        companyExample.add(new Company("Société Immobilière du Cap-Vert", "RENT", "https://upload.wikimedia.org/wikipedia/fr/6/66/Sicap_logo.jpg", new HashSet<>()));
        companyExample.add(new Company("UNCHK", "EDUCATION", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTHqs7it53GtRsCWrbyRAHc_y1oFKAdVupb_g&s", new HashSet<>()));
        companyExample.add(new Company("UCAD", "EDUCATION", "https://upload.wikimedia.org/wikipedia/commons/8/87/Logo_ucad_2.png", new HashSet<>()));
        companyExample.add(new Company("Le Lagon", "RESTAURANT", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT-X9sx3ebIoldGU_5-3C_lbPqdnFxGmJqBsA&s", new HashSet<>()));
        companyExample.add(new Company("Clinique Madeleine", "HEALTH", "https://jotalixibar.com/wp-content/uploads/2021/10/clinique-madeleine.png", new HashSet<>()));
        companyExample.add(new Company("Canal+", "ENTERTAINMENT", "https://upload.wikimedia.org/wikipedia/commons/thumb/5/5d/Logo_Canal%2B_1995.svg/2560px-Logo_Canal%2B_1995.svg.png", new HashSet<>()));
        companyExample.add(new Company("Dakar Dem Dikk", "TRANSPORT", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS4N65jrRApaRmsQ12aV3KgLovxi9_uv35Kbg&s", new HashSet<>()));
        companyExample.add(new Company("NSIA Assurances", "INSURANCE", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQwNqDC4R1kYBy4g4WctgYD2GMNJuQYPafiqQ&s", new HashSet<>()));
        companyExample.add(new Company("Exclusive Sénégal", "SHOPPING", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQCZHIxqG1oUyTZme6_P46Zpv05lOtGXbTSfA&s", new HashSet<>()));
        companyExample.add(new Company("Orange Money", "SUBSCRIPTION", "https://play-lh.googleusercontent.com/5bVuQv-mHv8fwgD9xsYklPMVjCWQiKOIZt5GnKIVwwNtHniuZqWnxqJKqpWHlTP7vALZ", new HashSet<>()));
        companyExample.add(new Company("Auchan", "GROCERIES", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTkQ3AUgc3dRLVrNDTkjHjgjAfEdjyjH3cr4Q&s", new HashSet<>()));
        companyExample.add(new Company("Fondation Servir le Sénégal", "CHARITY", "https://www.ndarinfo.com/photo/art/grande/6269909-9389547.jpg?v=1390847181", new HashSet<>()));
        companyExample.add(new Company("Ecobank", "BANK", "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5fFFZMV1klK3koLiZVkzZYpai8iDY7qRuRw&s", new HashSet<>()));
        
        companyRepository.saveAll(companyExample);
    }
}
