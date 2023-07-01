package com.example.assign.service.impl;

import com.example.assign.constant.SystemConstant;
import com.example.assign.converter.ProductConverter;
import com.example.assign.dto.CategoryDTO;
import com.example.assign.dto.ProductDTO;
import com.example.assign.entity.Product;
import com.example.assign.repo.ProductRepo;
import com.example.assign.service.CategoryService;
import com.example.assign.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;

    private final ProductConverter converter;

    private final CategoryService categoryService;

    @Override
    public ProductDTO addProduct(ProductDTO dto) {
        CategoryDTO category = categoryService.findCategoryByIdAndStatus(dto.getCategory_id(), SystemConstant.STATUS_AUTH);
        dto.setCategory(category);
        Product product = converter.toEntity(dto);
        return converter.toDTO(productRepo.save(product));
    }

    @Override
    public List<ProductDTO> findAllProduct() {
        return converter.toListDTO(productRepo.findAll());
    }

    @Override
    public ProductDTO findOneProductById(UUID id) {
        return converter.toDTO(productRepo.findById(id).get());
    }
}
