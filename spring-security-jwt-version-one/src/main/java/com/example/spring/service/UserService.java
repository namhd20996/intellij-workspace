package com.example.spring.service;

import com.example.spring.user.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findByEmail(String email);
}
