package com.example.demo.service.impl;

import com.example.demo.constant.SystemConstant;
import com.example.demo.converter.UserConverter;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repo.RoleRepo;
import com.example.demo.repo.UserRepo;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final UserConverter converter;

    private final JwtService jwtService;

    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Override
    public Optional<User> findUserByUsernameAndStatus(String username, Integer status) {
        return userRepo.findUserByUsernameAndStatus(username, status);
    }

    @Override
    public boolean existsUserByUsername(String username) {
        return userRepo.existsUserByUsername(username);
    }

    @Override
    public UserDTO register(UserDTO dto) {
        User user = null;
        UserDTO userDTO = null;
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        if (dto.getId() != null) {
            user = userRepo.findUserByUsernameAndStatus(dto.getUsername(), SystemConstant.STATUS_AUTH).get();
            user.setFirstname(dto.getFirstname());
            user.setLastname(dto.getLastname());
        } else {
            user = converter.toEntity(dto);
            user.setStatus(1);
            List<Role> roles = roleRepo.findRolesByName("ADMIN");
            user.setRoles(roles);
        }
        userDTO = converter.toDTO(userRepo.save(user));
        userDTO.setToken(jwtService.generateToken(user));
        return userDTO;
    }

    @Override
    public UserDTO authenticate(UserDTO dto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getUsername(),
                        dto.getPassword()
                )
        );
        User user = userRepo.findUserByUsernameAndStatus(dto.getUsername(), SystemConstant.STATUS_AUTH).get();
        UserDTO userDTO = converter.toDTO(user);
        userDTO.setToken(jwtService.generateToken(user));
        return userDTO;
    }

}
