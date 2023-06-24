package com.example.spring.service.impl;

import com.example.spring.entity.UserEntity;
import com.example.spring.repo.UserRepo;
import com.example.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    @Override
    public Optional<UserEntity> findUserEntityByEmail(String email) {
        return userRepo.findUserEntityByEmail(email);
    }
}
