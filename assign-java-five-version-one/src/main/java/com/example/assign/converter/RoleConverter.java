package com.example.assign.converter;

import com.example.assign.dto.RoleDTO;
import com.example.assign.entity.Role;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoleConverter {

    private final ModelMapper mapper;

    public Role toEntity(RoleDTO dto) {
        return Optional.ofNullable(dto)
                .map(role -> mapper.map(role, Role.class))
                .orElse(null);
    }

    public RoleDTO toDTO(Role entity) {
        return Optional.ofNullable(entity)
                .map(role -> mapper.map(role, RoleDTO.class))
                .orElse(null);
    }

}
