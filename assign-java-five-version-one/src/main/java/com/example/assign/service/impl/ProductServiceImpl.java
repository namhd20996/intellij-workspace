package com.example.assign.service.impl;

import com.example.assign.constant.SystemConstant;
import com.example.assign.converter.GalleryConverter;
import com.example.assign.converter.ProductConverter;
import com.example.assign.dto.CategoryDTO;
import com.example.assign.dto.ProductDTO;
import com.example.assign.entity.Gallery;
import com.example.assign.entity.Product;
import com.example.assign.repo.ProductRepo;
import com.example.assign.service.CategoryService;
import com.example.assign.service.GalleryService;
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

    private final GalleryService galleryService;

    @Override
    public ProductDTO addProduct(ProductDTO dto) {
        CategoryDTO category = categoryService.findCategoryByIdAndStatus(dto.getCategory_key(), SystemConstant.STATUS_CATEGORY);
        dto.setCategory(category);
        List<Gallery> galleries = galleryConverter.toListEntity(dto.getGalleries());

        Product product = productConverter.toEntity(dto);
        Product productSave = productRepo.save(product);

        galleries.forEach(gallery -> gallery.setProduct(productSave));

        galleryService.addAllGallery(galleryConverter.toListDTO(galleries));

        return productConverter.toDTO(productSave);
    }

    @Override
    public List<ProductDTO> findAllProduct() {
        return productConverter.toListDTO(productRepo.findAll());
    }

    @Override
    public ProductDTO findOneProductById(UUID id) {
        return productConverter.toDTO(productRepo.findById(id).get());
    }

    @Override
    public List<ProductDTO> findAllByCategoryId(UUID id) {
        return productConverter.toListDTO(productRepo.findAllByCategoryId(id));
    }

    @Override
    public void updateQuantityByIdAndStatus(Integer quantity, UUID id, Integer status) {
        productRepo.updateQuantityByIdAndStatus(quantity, id, status);
    }
}
