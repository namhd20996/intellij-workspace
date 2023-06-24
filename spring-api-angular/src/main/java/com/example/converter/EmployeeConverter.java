package com.example.converter;

import com.example.dto.EmployeeDTO;
import com.example.entity.EmployeeEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class EmployeeConverter {

    @Autowired
    private ModelMapper mapper;

    public EmployeeEntity toEntity(EmployeeDTO dto) {
        EmployeeEntity entity = null;
        if (dto != null) {
            entity = mapper.map(dto, EmployeeEntity.class);
        }
        return entity;
    }

    public EmployeeDTO toDTO(EmployeeEntity entity) {
        EmployeeDTO dto = null;
        if (entity != null) {
            dto = mapper.map(entity, EmployeeDTO.class);
        }
        return dto;
    }

    public List<EmployeeEntity> toListEntity(List<EmployeeDTO> dtos) {
        List<EmployeeEntity> results = Arrays.asList(mapper.map(dtos, EmployeeEntity[].class));
        return results;
    }

    public List<EmployeeDTO> toListDTO(List<EmployeeEntity> entities){
        List<EmployeeDTO> results = Arrays.asList(mapper.map(entities, EmployeeDTO[].class));
        return results;
    }
}
