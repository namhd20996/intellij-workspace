package com.example.assign.product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    void addProduct(UUID uuid, ProductAddRequest request);

    boolean existsByName(String name);

    List<ProductDTO> findAllProduct();

    ProductDTO findOneProductById(UUID id);

    ProductDTO findProductByIdAndStatus(UUID uuid, Integer status);

    List<ProductDTO> findAllByCategoryId(UUID id);

    void updateQuantityByIdAndStatus(Integer quantity, UUID id, Integer status);
}
