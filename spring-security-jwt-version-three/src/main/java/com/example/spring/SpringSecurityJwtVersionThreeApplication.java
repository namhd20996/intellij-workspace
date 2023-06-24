package com.example.spring;

import com.example.spring.auth.RegisterRequest;
import com.example.spring.entity.Role;
import com.example.spring.service.impl.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSecurityJwtVersionThreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtVersionThreeApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService service) {
        return args -> {
            var user = RegisterRequest.builder()
                    .username("user")
                    .firstname("user")
                    .lastname("user")
                    .email("user@gmail.com")
                    .password("123")
                    .role(Role.USER)
                    .build();
            System.out.println("USER TOKEN: " + service.register(user).getToken());

            var admin = RegisterRequest.builder()
                    .username("admin")
                    .firstname("admin")
                    .lastname("admin")
                    .email("admin@gmail.com")
                    .password("123")
                    .role(Role.ADMIN)
                    .build();
            System.out.println("ADMIN TOKEN: " + service.register(admin).getToken());

            var manager = RegisterRequest.builder()
                    .username("manager")
                    .firstname("manager")
                    .lastname("manager")
                    .email("manager@gmail.com")
                    .password("123")
                    .role(Role.MANAGER)
                    .build();
            System.out.println("MANAGER TOKEN: " + service.register(manager).getToken());

            var admin_fake = RegisterRequest.builder()
                    .username("adminfake")
                    .firstname("adminfake")
                    .lastname("adminfake")
                    .email("adminfake@gmail.com")
                    .password("123")
                    .role(Role.ADMIN_FAKE)
                    .build();
            System.out.println("ADMIN_FAKE TOKEN: " + service.register(admin_fake).getToken());
        };
    }
}
