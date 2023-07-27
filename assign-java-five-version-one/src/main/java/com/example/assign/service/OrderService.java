package com.example.assign.service;

import com.example.assign.dto.OrderDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderDTO> findAllByIdAndStatus(UUID id, Integer status);

    List<OrderDTO> findAll();

    OrderDTO findOrderById(UUID id);

    OrderDTO addOrder(OrderDTO dto);
}
