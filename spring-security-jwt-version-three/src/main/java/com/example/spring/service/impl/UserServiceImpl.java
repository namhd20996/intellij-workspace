package com.example.spring.service.impl;

import com.example.spring.entity.User;
import com.example.spring.repo.UserRepo;
import com.example.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;


    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepo.findUserByUsername(username);
    }
}
