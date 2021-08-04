package com.example.mockserver;

import com.example.mockserver.securityConfig.JwtTokenFilter;
import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MockServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MockServerApplication.class, args);
        //MockServer mockServer = new MockServer();
        // mockServer.expectations();

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
