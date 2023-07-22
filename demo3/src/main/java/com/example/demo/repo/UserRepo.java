package com.example.demo.repo;


import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsernameAndStatus(String username, Integer status);

    boolean existsUserByUsername(String username);
}
