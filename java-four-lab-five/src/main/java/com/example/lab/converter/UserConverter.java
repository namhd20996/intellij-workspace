package com.example.lab.converter;

import com.example.lab.dto.UserDTO;
import com.example.lab.entity.User;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

public class UserConverter {

    private final ModelMapper mapper = new ModelMapper();

    public static UserConverter getInstance() {
        UserConverter u = null;
        if (u == null) {
            u = new UserConverter();
        }
        return u;
    }

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

    public List<User> toListEntity(List<UserDTO> dtoList) {
        return Optional.of(dtoList).map(users -> List.of(mapper.map(users, User[].class))).orElse(null);
    }

    public List<UserDTO> toListDTO(List<User> entities) {
        return Optional.of(entities)
                .map(users -> List.of(mapper.map(users, UserDTO[].class)))
                .orElse(null);
    }
}
