package com.example.assign.converter;

import com.example.assign.api.output.AuthenticationResp;
import com.example.assign.dto.UserDTO;
import com.example.assign.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final ModelMapper mapper;

    public User toEntity(UserDTO dto) {
        return Optional.ofNullable(dto)
                .map(user -> mapper.map(user, User.class))
                .orElse(null);
    }

    public UserDTO toDTO(User entity) {
        return Optional.ofNullable(entity)
                .map(user -> mapper.map(user, UserDTO.class))
                .orElse(null);
    }

    public List<User> toListEntity(List<UserDTO> dtos) {
        return Optional.of(List.of(mapper.map(dtos, User[].class))).orElse(null);
    }

    public List<UserDTO> toListDTO(List<User> entities) {
        return Optional.of(List.of(mapper.map(entities, UserDTO[].class))).orElse(null);
    }

    public AuthenticationResp authenticationResp(UserDTO dto) {
        return Optional.ofNullable(dto)
                .map(user -> mapper.map(user, AuthenticationResp.class))
                .orElse(null);
    }

    public List<AuthenticationResp> authenticationResp(List<UserDTO> dtoList) {
        return Optional.of(dtoList)
                .map(users -> List.of(mapper.map(users, AuthenticationResp[].class)))
                .orElse(null);
    }
}
