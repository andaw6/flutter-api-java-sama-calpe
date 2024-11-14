package com.ehacdev.flutter_api_java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Authentification")
                        .version("1.0")
                        .description("Cette API permet de gérer l'authentification des clients et des utilisateurs"));
    }
}
