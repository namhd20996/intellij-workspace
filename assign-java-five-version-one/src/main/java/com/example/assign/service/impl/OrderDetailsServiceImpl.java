package com.example.assign.service.impl;

import com.example.assign.converter.OrderDetailsConverter;
import com.example.assign.dto.OrderDetailsDTO;
import com.example.assign.entity.OrderDetails;
import com.example.assign.repo.OrderDetailsRepo;
import com.example.assign.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private final OrderDetailsRepo orderDetailsRepo;

    private final OrderDetailsConverter orderDetailsConverter;


    @Override
    public OrderDetailsDTO addOrderDetail(OrderDetailsDTO dto) {
        OrderDetails orderDetails = orderDetailsConverter.toEntity(dto);
        return orderDetailsConverter.toDTO(orderDetailsRepo.save(orderDetails));
    }

    @Override
    public void addAllOrderDetail(List<OrderDetails> dtoList) {
        orderDetailsRepo.saveAll(dtoList);
    }

    @Override
    public List<OrderDetailsDTO> findProductsByEmail(String email) {
        return orderDetailsRepo.findProductsByEmail(email)
                .stream()
                .map(orderDetailsConverter::toDTO)
                .toList();
    }
}
