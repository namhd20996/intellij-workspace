package com.example.assign.converter;

import com.example.assign.api.output.GalleryOutput;
import com.example.assign.api.output.ProductOutPut;
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
        return Optional.of(dtoList).map(products -> List.of(mapper.map(products, Product[].class))).orElse(null);
    }

    public List<ProductDTO> toListDTO(List<Product> entities) {
        return Optional.of(entities).map(products -> List.of(mapper.map(products, ProductDTO[].class))).orElse(null);
    }

    public List<ProductOutPut> productOutPutList(List<ProductDTO> dtoList) {
        List<ProductOutPut> list = new ArrayList<>();
        dtoList
                .forEach(product -> {
                    var productOut = ProductOutPut.builder()
                            .id(product.getId())
                            .createdDate(product.getCreatedDate())
                            .createdBy(product.getCreatedBy())
                            .lastModifiedDate(product.getLastModifiedDate())
                            .lastModifiedBy(product.getLastModifiedBy())
                            .name(product.getName())
                            .price(product.getPrice())
                            .quantity(product.getQuantity())
                            .shortDescription(product.getShortDescription())
                            .longDescription(product.getLongDescription())
                            .category_id(product.getCategory().getId())
                            .discount(product.getDiscount())
                            .galleries(List.of(
                                    mapper.map(product.getGalleries(), GalleryOutput[].class)))
                            .build();
                    list.add(productOut);
                });
        return list;
    }
}
