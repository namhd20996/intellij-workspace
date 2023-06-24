package com.example.spring.service;

import com.example.spring.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> findUserEntityByEmail(String email);
}
