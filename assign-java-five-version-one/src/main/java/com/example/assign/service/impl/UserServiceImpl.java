package com.example.assign.service.impl;

import com.example.assign.constant.SystemConstant;
import com.example.assign.converter.UserConverter;
import com.example.assign.dto.UserDTO;
import com.example.assign.entity.Role;
import com.example.assign.entity.Token;
import com.example.assign.entity.User;
import com.example.assign.repo.RoleRepo;
import com.example.assign.repo.TokenRepo;
import com.example.assign.repo.UserRepo;
import com.example.assign.service.JwtService;
import com.example.assign.service.UserService;
import com.example.assign.sysenum.TokenType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    private final UserConverter converter;

    private final JwtService jwtService;

    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenRepo tokenRepo;

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
        user = userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        userDTO = converter.toDTO(user);
        userDTO.setToken(jwtToken);
        saveUserToken(user, jwtToken);
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
        var jwtToken = jwtService.generateToken(user);
        userDTO.setToken(jwtToken);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return userDTO;
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepo.findAllValidTokensByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepo.saveAll(validUserTokens);
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .tokenType(TokenType.BEARER)
                .token(jwtToken)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepo.save(token);
    }

}
