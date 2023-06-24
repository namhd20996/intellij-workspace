package com.example.spring.service;

import com.example.spring.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUsername(String username);
}
