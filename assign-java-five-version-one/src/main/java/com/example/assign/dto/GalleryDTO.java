package com.example.assign.dto;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GalleryDTO extends BaseDTO<GalleryDTO>{

    private String thumbnail;
    private ProductDTO product;
}
