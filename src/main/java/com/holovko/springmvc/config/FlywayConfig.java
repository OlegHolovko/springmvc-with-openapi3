package com.holovko.springmvc.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlywayConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.flyway.locations}")
    private String locations;

    @Bean
    public void getMigration(){
        Flyway flyway = Flyway.configure()
                .locations(locations).dataSource(url, user, password).load();
        flyway.migrate();
    }
}
