package com.example.assign.api.output;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryResp {

    private UUID id;
    private String name;
    private String description;
    private String image;
    private Integer status;
    private String message;
}
