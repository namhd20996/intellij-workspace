package com.example.assign.converter;


import com.example.assign.api.output.OrderDetailsResp;
import com.example.assign.dto.OrderDetailsDTO;
import com.example.assign.entity.OrderDetails;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderDetailsConverter {

    private final ModelMapper mapper;

    public OrderDetails toEntity(OrderDetailsDTO dto) {
        return Optional.ofNullable(dto)
                .map(orderDetails -> mapper.map(orderDetails, OrderDetails.class))
                .orElse(null);
    }

    public OrderDetailsDTO toDTO(OrderDetails entity) {
        return Optional.ofNullable(entity)
                .map(orderDetails -> mapper.map(orderDetails, OrderDetailsDTO.class))
                .orElse(null);
    }

    public List<OrderDetails> toListEntity(List<OrderDetailsDTO> dtoList) {
        return Optional.of(dtoList).map(orderDetails -> List.of(mapper.map(orderDetails, OrderDetails[].class))).orElse(null);
    }

    public List<OrderDetailsDTO> toListDTO(List<OrderDetails> entities) {
        return Optional.of(entities).map(orderDetails -> List.of(mapper.map(orderDetails, OrderDetailsDTO[].class))).orElse(null);
    }

    public OrderDetailsResp orderDetailsResp(OrderDetailsDTO dto) {
        return Optional.ofNullable(dto)
                .map(orderDetails -> mapper.map(orderDetails, OrderDetailsResp.class))
                .orElse(null);
    }

    public List<OrderDetailsResp> orderDetailsResp(List<OrderDetailsDTO> dtoList) {
        return Optional.of(dtoList).map(orderDetails -> List.of(mapper.map(orderDetails, OrderDetailsResp[].class))).orElse(null);
    }
}
