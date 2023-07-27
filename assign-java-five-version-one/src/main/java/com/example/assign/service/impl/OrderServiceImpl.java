package com.example.assign.service.impl;

import com.example.assign.constant.SystemConstant;
import com.example.assign.converter.OrderConverter;
import com.example.assign.dto.OrderDTO;
import com.example.assign.entity.OrderDetails;
import com.example.assign.entity.User;
import com.example.assign.exception.ApiRequestException;
import com.example.assign.repo.OrderRepo;
import com.example.assign.repo.ProductRepo;
import com.example.assign.service.OrderDetailsService;
import com.example.assign.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepo orderRepo;

    private final OrderConverter orderConverter;


    private final ProductRepo productRepo;

    private final OrderDetailsService orderDetailsService;


    @Override
    public List<OrderDTO> findAllByIdAndStatus(UUID id, Integer status) {
        return orderConverter.toListDTO(orderRepo.findAllByIdAndStatus(id, status));
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderConverter.toListDTO(orderRepo.findAll());
    }

    @Override
    public OrderDTO findOrderById(UUID id) {
        return orderRepo.findById(id)
                .map(orderConverter::toDTO)
                .orElseThrow(() -> new ApiRequestException("Order id: " + id + "not found!.."));
    }

    @Override
    public OrderDTO addOrder(OrderDTO dto) {
        List<OrderDetails> orderDetailsList = dto.getProducts()
                .stream().parallel()
                .map(product -> {
                    var productEntity = productRepo.findById(product.getId())
                            .orElseThrow(() -> new ApiRequestException("Product: " + product.getId() + "not found!.."));
                    if (productEntity.getQuantity() == 0 || productEntity.getQuantity() - product.getQuantity() < 0)
                        throw new ApiRequestException(productEntity.getName() + " invalid quantity");
                    productRepo.updateQuantityByIdAndStatus(
                            productEntity.getQuantity() - product.getQuantity(),
                            productEntity.getId(),
                            SystemConstant.STATUS_PRODUCT
                    );
                    return OrderDetails.builder()
                            .product(productEntity)
                            .price(product.getPrice())
                            .quantity(product.getQuantity())
                            .totalMoney(product.getPrice() * product.getQuantity())
                            .build();
                }).toList();
        double totalMoney = orderDetailsList.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantity())
                .sum();
        var order = orderConverter.toEntity(dto);
        order.setOrderDetails(orderDetailsList);
        order.setTotalMoney(totalMoney);
        order.setStatus(SystemConstant.STATUS_ORDER_APPROVE);
        order.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        var finalOrder = orderRepo.save(order);
        orderDetailsList.forEach(orderDetails -> orderDetails.setOrder(finalOrder));

        orderDetailsService.addAllOrderDetail(orderDetailsList);
        OrderDTO orderDTO = orderConverter.toDTO(finalOrder);
        orderDTO.setProducts(dto.getProducts());
        return orderDTO;
    }
}
