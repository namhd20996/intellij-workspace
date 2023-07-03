package com.example.assign.service;

import com.example.assign.dto.CategoryDTO;
import com.example.assign.dto.ProductDTO;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductDTO addProduct(ProductDTO dto);

    List<ProductDTO> findAllProduct();

    ProductDTO findOneProductById(UUID id);

    List<ProductDTO> findAllByCategoryId(UUID id);
}
