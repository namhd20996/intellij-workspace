package com.example.demo.service;


import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUsernameAndStatus(String username, Integer status);

    boolean existsUserByUsername(String username);

    UserDTO register(UserDTO dto);

    UserDTO authenticate(UserDTO dto);
}
