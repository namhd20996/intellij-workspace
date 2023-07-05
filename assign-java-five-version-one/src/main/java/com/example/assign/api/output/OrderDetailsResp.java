package com.example.assign.api.output;

import com.example.assign.dto.OrderDTO;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsResp {

    private Double price;
    private Integer quantity;
    private Double totalMoney;
    private ProductOut product;
    private OrderResp order;
}
