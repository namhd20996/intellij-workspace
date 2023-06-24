package com.example.spring;

import com.example.spring.auth.RegisterRequest;
import com.example.spring.entity.Role;
import com.example.spring.entity.User;
import com.example.spring.repo.RoleRepo;
import com.example.spring.repo.UserRepo;
import com.example.spring.service.impl.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityJwtVersionThreeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtVersionThreeApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AuthenticationService service, RoleRepo roleRepo, UserRepo userRepo) {
        return args -> {
            Role a = new Role("ADMIN", "admin:read");
            Role aa = new Role("ADMIN", "admin:create");
            Role aaa = new Role("ADMIN", "admin:update");
            Role aaaa = new Role("ADMIN", "admin:delete");
            Role u = new Role("USER", "user:read");
            Role m = new Role("MANAGER", "manager:read");
            roleRepo.save(a);
            roleRepo.save(aa);
            roleRepo.save(aaa);
            roleRepo.save(aaaa);
            roleRepo.save(u);
            roleRepo.save(m);

            List<Role> roles = List.of(a, aa, aaa, aaaa);
            var user = RegisterRequest.builder()
                    .username("user")
                    .firstname("user")
                    .lastname("user")
                    .email("user@gmail.com")
                    .password("123")
                    .roles(roles)
                    .build();
            System.out.println("USER TOKEN: " + service.register(user).getToken());

            var admin = RegisterRequest.builder()
                    .username("admin")
                    .firstname("admin")
                    .lastname("admin")
                    .email("admin@gmail.com")
                    .password("123")
                    .roles(roles)
                    .build();
            System.out.println("ADMIN TOKEN: " + service.register(admin).getToken());

            var manager = RegisterRequest.builder()
                    .username("manager")
                    .firstname("manager")
                    .lastname("manager")
                    .email("manager@gmail.com")
                    .password("123")
                    .roles(roles)
                    .build();
            System.out.println("MANAGER TOKEN: " + service.register(manager).getToken());

            var admin_fake = RegisterRequest.builder()
                    .username("adminfake")
                    .firstname("adminfake")
                    .lastname("adminfake")
                    .email("adminfake@gmail.com")
                    .password("123")
                    .roles(roles)
                    .build();
            System.out.println("ADMIN_FAKE TOKEN: " + service.register(admin_fake).getToken());
        };
    }
}
