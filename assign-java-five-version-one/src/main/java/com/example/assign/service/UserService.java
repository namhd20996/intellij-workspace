package com.example.assign.service;

import com.example.assign.dto.UserDTO;
import com.example.assign.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUsernameAndStatus(String username, Integer status);

    boolean existsUserByUsername(String username);

    UserDTO register(UserDTO dto);

    UserDTO authenticate(UserDTO dto);
}
