package com.example.assign.api.output;

import com.example.assign.dto.CategoryDTO;
import com.example.assign.dto.GalleryDTO;
import com.example.assign.dto.ProductDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductOutPut extends BaseOutput<ProductDTO> {
    private UUID id;
    private Date createdDate;
    private Date lastModifiedDate;
    private String createdBy;
    private String lastModifiedBy;
    private String name;
    private String shortDescription;
    private String longDescription;
    private Double price;
    private Integer quantity;
    private Double discount;
    private UUID category_id;
    private List<GalleryOutput> galleries = new ArrayList<>();
}
