package com.example.assign.service;

import com.example.assign.dto.UserDTO;
import com.example.assign.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> findUserByUsernameAndStatus(String username, Integer status);

    boolean existsUserByUsername(String username);

    UserDTO register(UserDTO dto);

    UserDTO authenticate(UserDTO dto);

    String confirmToken(String token);

    UserDTO updateUserByRole(UUID uuid, String authorize);

    UserDTO deleteUserByRole(UUID uuid, String authorize);
}
