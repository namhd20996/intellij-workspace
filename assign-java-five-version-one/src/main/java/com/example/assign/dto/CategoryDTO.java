package com.example.assign.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO extends BaseDTO<CategoryDTO> {

    private String name;
    private String description;
    private String image;
    @JsonIgnore
    private Integer status;
    @JsonIgnore
    private List<ProductDTO> products = new ArrayList<>();
}
