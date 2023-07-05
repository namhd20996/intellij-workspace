package com.example.assign.converter;

import com.example.assign.api.output.GalleryResp;
import com.example.assign.api.output.ProductResp;
import com.example.assign.dto.ProductDTO;
import com.example.assign.entity.Product;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductConverter {

    private final ModelMapper mapper;

    public Product toEntity(ProductDTO dto) {
        return Optional.ofNullable(dto)
                .map(product -> mapper.map(product, Product.class))
                .orElse(null);
    }

    public ProductDTO toDTO(Product entity) {
        return Optional.ofNullable(entity)
                .map(product -> mapper.map(product, ProductDTO.class))
                .orElse(null);
    }

    public List<Product> toListEntity(List<ProductDTO> dtoList) {
        return Optional.of(dtoList)
                .map(products -> List.of(mapper.map(products, Product[].class)))
                .orElse(null);
    }

    public List<ProductDTO> toListDTO(List<Product> entities) {
        return Optional.of(entities)
                .map(products -> List.of(mapper.map(products, ProductDTO[].class)))
                .orElse(null);
    }

    public List<ProductResp> productOutPutList(List<ProductDTO> dtoList) {
        return Optional.of(dtoList)
                .map(products -> List.of(mapper.map(products, ProductResp[].class)))
                .orElse(null);
    }

    public ProductResp productOutPut(ProductDTO dto) {
        return Optional.ofNullable(dto)
                .map(product -> mapper.map(product, ProductResp.class))
                .orElse(null);
    }
}
