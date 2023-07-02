package com.example.assign.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO extends BaseDTO<ProductDTO> {

    private String name;
    private String shortDescription;
    private String longDescription;
    private Double price;
    private Integer quantity;
    private Double discount;
    private UUID category_key;
    private UserDTO user;
    private CategoryDTO category;
    private List<GalleryDTO> galleries = new ArrayList<>();
    private List<OrderDetailsDTO> orderDetails = new ArrayList<>();
}
