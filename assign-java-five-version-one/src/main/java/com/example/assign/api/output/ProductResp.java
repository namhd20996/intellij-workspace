package com.example.assign.api.output;

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
public class ProductResp {
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
    private CategoryResp category;
    private List<GalleryResp> galleries = new ArrayList<>();
}
