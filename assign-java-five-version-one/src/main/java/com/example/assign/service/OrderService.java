package com.example.assign.service;

import com.example.assign.api.output.OrderResp;
import com.example.assign.dto.OrderDTO;
import com.example.assign.entity.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    List<OrderDTO> findAllByIdAndStatus(UUID id, Integer status);

    List<OrderDTO> findAll();

    OrderDTO findOrderById(UUID id);

    OrderResp addOrder(OrderDTO dto);
}
