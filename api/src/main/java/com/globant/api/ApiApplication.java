package com.globant.api;

import com.globant.api.securityConfig.JwtTokenFilter;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableFeignClients
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
        MockServer mockServer = new MockServer();
        mockServer.expectations();

        Flyway flyway = Flyway
                .configure()
                .dataSource("jdbc:mysql://localhost:3306/sampledb", "root", "root")
                .load();
        //flyway.clean();
        flyway.migrate();
    }

    @Bean
    JwtTokenFilter jwtTokenFilter() {
        return new JwtTokenFilter();
    }


}
