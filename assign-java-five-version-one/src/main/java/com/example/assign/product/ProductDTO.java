package com.example.assign.product;

import com.example.assign.category.CategoryDTO;
import com.example.assign.gallery.GalleryDTO;
import com.example.assign.orderdetails.OrderDetailsDTO;
import com.example.assign.user.UserDTO;
import com.example.assign.util.BaseDTO;
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
    @JsonIgnore
    private UserDTO user;
    private CategoryDTO category;
    private List<GalleryDTO> galleries = new ArrayList<>();
    @JsonIgnore
    private List<OrderDetailsDTO> orderDetails = new ArrayList<>();
}
