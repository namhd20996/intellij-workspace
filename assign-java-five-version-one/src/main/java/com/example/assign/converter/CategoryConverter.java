package com.example.assign.converter;

import com.example.assign.dto.CategoryDTO;
import com.example.assign.entity.Category;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    private final ModelMapper mapper;

    public Category toEntity(CategoryDTO dto) {
        return Optional.ofNullable(dto)
                .map(category -> mapper.map(category, Category.class))
                .orElse(null);
    }

    public CategoryDTO toDTO(Category entity) {
        return Optional.ofNullable(entity)
                .map(category -> mapper.map(category, CategoryDTO.class))
                .orElse(null);
    }

    public List<Category> toListEntity(List<CategoryDTO> dtos) {
        return Optional.of(dtos).map(categories -> List.of(mapper.map(categories, Category[].class))).orElse(null);
    }

    public List<CategoryDTO> toListDTO(List<Category> entities) {
        return Optional.of(entities).map(categories -> List.of(mapper.map(categories, CategoryDTO[].class))).orElse(null);
    }
}
