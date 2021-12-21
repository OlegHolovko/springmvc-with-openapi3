package com.holovko.springmvc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI(@Value("Descriontion") String appDesciption, @Value("3.0.0") String appVersion) {
        return new OpenAPI()
                .info(new Info()
                    .title("Test assignment API")
                    .version("3.0.0")
                    .description("This is a Spring Boot RESTful service using springdoc-openapi and OpenAPI 3.")
                    .termsOfService("http://swagger.io/terms/")
                    .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}