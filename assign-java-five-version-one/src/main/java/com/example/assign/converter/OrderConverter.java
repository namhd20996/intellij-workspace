package com.example.assign.converter;

import com.example.assign.dto.OrderDTO;
import com.example.assign.entity.Order;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderConverter {

    private final ModelMapper mapper;

    public Order toEntity(OrderDTO dto) {
        return Optional.ofNullable(dto)
                .map(order -> mapper.map(order, Order.class))
                .orElse(null);
    }

    public OrderDTO toDTO(Order entity) {
        return Optional.ofNullable(entity)
                .map(order -> mapper.map(order, OrderDTO.class))
                .orElse(null);
    }

    public List<OrderDTO> toListDTO(List<Order> entities) {
        return Optional.of(entities).map(orders -> List.of(mapper.map(orders, OrderDTO[].class))).orElse(null);
    }

}
