package com.example.assign.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetailsDTO extends BaseDTO<OrderDetailsDTO> {

    private Double price;
    private Integer quantity;
    private Double totalMoney;
    private ProductDTO product;
    private OrderDTO order;
}
