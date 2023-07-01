package com.example.assign.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO extends BaseDTO<CategoryDTO> {

    private String name;
    private String description;
    private String image;
    private Integer status;
    private List<ProductDTO> products = new ArrayList<>();
}
