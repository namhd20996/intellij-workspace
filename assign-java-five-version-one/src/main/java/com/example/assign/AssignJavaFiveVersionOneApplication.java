package com.example.assign;

import com.example.assign.converter.UserConverter;
import com.example.assign.dto.UserDTO;
import com.example.assign.entity.Role;
import com.example.assign.entity.User;
import com.example.assign.repo.RoleRepo;
import com.example.assign.repo.UserRepo;
import com.example.assign.service.RoleService;
import com.example.assign.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AssignJavaFiveVersionOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(AssignJavaFiveVersionOneApplication.class, args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(UserRepo userService, RoleRepo roleRepo, UserConverter userConverter) {
//        return args -> {
//            Role a = new Role("ADMIN", "admin:read");
//            Role aa = new Role("ADMIN", "admin:create");
//            Role aaa = new Role("ADMIN", "admin:update");
//            Role aaaa = new Role("ADMIN", "admin:delete");
//            Role u = new Role("USER", "user:read");
//            Role m = new Role("MANAGER", "manager:read");
//            roleRepo.save(a);
//            roleRepo.save(aa);
//            roleRepo.save(aaa);
//            roleRepo.save(aaaa);
//        };
//    }
}
