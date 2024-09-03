package com.uzay.multiplerolebasedauthenticationapia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "AwareConfig")
@SpringBootApplication
public class MultipleRoleBasedAuthenticationApi1Application {

    public static void main(String[] args) {
        SpringApplication.run(MultipleRoleBasedAuthenticationApi1Application.class, args);
    }

}
