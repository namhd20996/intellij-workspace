package com.example.assign.service;

import com.example.assign.dto.OrderDetailsDTO;
import com.example.assign.dto.ProductDTO;
import com.example.assign.entity.OrderDetails;
import com.example.assign.entity.Product;

import java.util.List;

public interface OrderDetailsService {

    OrderDetailsDTO addOrderDetail(OrderDetailsDTO dto);

    void addAllOrderDetail(List<OrderDetails> dtoList);

    List<OrderDetailsDTO> findProductsByEmail(String email);
}
