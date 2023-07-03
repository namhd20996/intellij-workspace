package com.example.assign.api.output;

import com.example.assign.dto.CategoryDTO;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryOutput extends BaseOutput<CategoryDTO> {

    private UUID id;
    private String name;
    private String description;
    private String image;
    private Integer status;
    private String message;
}
