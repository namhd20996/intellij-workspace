package com.example.assign.orderdetails;

import com.example.assign.order.OrderDTO;
import com.example.assign.product.ProductDTO;
import com.example.assign.util.BaseDTO;
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
