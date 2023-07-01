package com.example.assign.service.impl;

import com.example.assign.converter.CategoryConverter;
import com.example.assign.dto.CategoryDTO;
import com.example.assign.entity.Category;
import com.example.assign.repo.CategoryRepo;
import com.example.assign.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;

    private final CategoryConverter converter;

    @Override
    public CategoryDTO addCategory(CategoryDTO dto) {
        Category category = converter.toEntity(dto);
        category.setStatus(1);
        return converter.toDTO(categoryRepo.save(category));
    }

    @Override
    public List<CategoryDTO> findAllCategory() {
        return converter.toListDTO(categoryRepo.findAll());
    }

    @Override
    public CategoryDTO findOneById(UUID id) {
        return converter.toDTO(categoryRepo.findById(id).get());
    }

    @Override
    public CategoryDTO findCategoryByIdAndStatus(UUID id, Integer status) {
        return converter.toDTO(categoryRepo.findCategoryByIdAndStatus(id, status));
    }
}
