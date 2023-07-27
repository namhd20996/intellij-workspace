package com.example.assign.service.impl;

import com.example.assign.constant.SystemConstant;
import com.example.assign.converter.GalleryConverter;
import com.example.assign.converter.ProductConverter;
import com.example.assign.dto.CategoryDTO;
import com.example.assign.dto.ProductDTO;
import com.example.assign.entity.Gallery;
import com.example.assign.entity.Product;
import com.example.assign.exception.ApiRequestException;
import com.example.assign.repo.GalleryRepo;
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

    private final ProductConverter productConverter;

    private final GalleryConverter galleryConverter;

    private final CategoryService categoryService;

    private final GalleryRepo galleryRepo;

    @Override
    public ProductDTO addProduct(ProductDTO dto) {
        CategoryDTO category = categoryService.findCategoryByIdAndStatus(dto.getCategory_key(), SystemConstant.STATUS_CATEGORY);
        dto.setCategory(category);
        List<Gallery> galleries = galleryConverter.toListEntity(dto.getGalleries());
        Product productSave = productRepo.save(productConverter.toEntity(dto));
        galleries.forEach(gallery -> gallery.setProduct(productSave));
        productSave.setGalleries(galleryRepo.saveAll(galleries));
        return productConverter.toDTO(productSave);
    }

    @Override
    public List<ProductDTO> findAllProduct() {
        return productConverter.toListDTO(productRepo.findAll());
    }

    @Override
    public ProductDTO findOneProductById(UUID id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new ApiRequestException("Product id: " + id + "not found!.."));
        return productConverter.toDTO(product);
    }

    @Override
    public List<ProductDTO> findAllByCategoryId(UUID id) {
        List<Product> products = productRepo.findAllByCategoryId(id)
                .orElseThrow(() -> new ApiRequestException("Category id: " + id + "not found!.."));
        return productConverter.toListDTO(products);
    }

    @Override
    public void updateQuantityByIdAndStatus(Integer quantity, UUID id, Integer status) {
        productRepo.updateQuantityByIdAndStatus(quantity, id, status);
    }
}
