package com.example.assign.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
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
    @JsonIgnore
    private UserDTO user;
    private CategoryDTO category;
    private List<GalleryDTO> galleries = new ArrayList<>();
    @JsonIgnore
    private List<OrderDetailsDTO> orderDetails = new ArrayList<>();
}
