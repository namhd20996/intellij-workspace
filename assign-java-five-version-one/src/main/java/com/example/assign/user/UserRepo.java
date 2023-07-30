package com.example.assign.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<com.example.assign.user.User, UUID> {
    Optional<com.example.assign.user.User> findUserByUsernameAndStatus(String username, Integer status);

    boolean existsUserByUsername(String username);

    boolean existsByEmail(String email);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("""
            UPDATE User u
            SET u.status = 1
            WHERE u.email =?1
            """)
    void updateUserByEmail(String email);

}
