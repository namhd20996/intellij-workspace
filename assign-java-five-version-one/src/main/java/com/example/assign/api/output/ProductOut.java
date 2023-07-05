package com.example.assign.api.output;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductOut {

    private UUID id;
    private String name;
    private Double price;
    private Integer quantity;
    private Double discount;
}
