package com.example.assign.repo;

import com.example.assign.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {
    Optional<User> findUserByUsernameAndStatus(String username, Integer status);

    boolean existsUserByUsername(String username);

}
