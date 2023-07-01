package com.example.assign.service.impl;

import com.example.assign.converter.OrderConverter;
import com.example.assign.dto.OrderDTO;
import com.example.assign.entity.Order;
import com.example.assign.repo.OrderRepo;
import com.example.assign.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    private final OrderConverter converter;

    @Override
    public List<OrderDTO> findAllByIdAndStatus(UUID id, Integer status) {
        return converter.toListDTO(orderRepo.findAllByIdAndStatus(id, status));
    }

    @Override
    public List<OrderDTO> findAll() {
        return converter.toListDTO(orderRepo.findAll());
    }

    @Override
    public OrderDTO findOrderById(UUID id) {
        return converter.toDTO(orderRepo.findById(id).get());
    }

    @Override
    public OrderDTO addOrder(OrderDTO dto) {
        Order order = converter.toEntity(dto);
        return converter.toDTO(orderRepo.save(order));
    }
}
