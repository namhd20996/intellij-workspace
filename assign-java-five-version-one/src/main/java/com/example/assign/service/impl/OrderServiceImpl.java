package com.example.assign.service.impl;

import com.example.assign.api.output.OrderResp;
import com.example.assign.constant.SystemConstant;
import com.example.assign.converter.OrderConverter;
import com.example.assign.converter.ProductConverter;
import com.example.assign.dto.OrderDTO;
import com.example.assign.entity.Order;
import com.example.assign.entity.OrderDetails;
import com.example.assign.entity.User;
import com.example.assign.repo.OrderRepo;
import com.example.assign.service.OrderDetailsService;
import com.example.assign.service.OrderService;
import com.example.assign.service.ProductService;
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

    private final ProductService productService;

    private final OrderDetailsService orderDetailsService;

    private final ProductConverter productConverter;

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
        return orderConverter.toDTO(orderRepo.findById(id).orElse(null));
    }

    @Override
    public OrderResp addOrder(OrderDTO dto) {
        List<OrderDetails> orderDetailsList = dto.getProducts()
                .stream().parallel()
                .map(product -> {
                    var productEntity = productConverter.toEntity(productService.findOneProductById(product.getId()));
                    if (productEntity.getQuantity() == 0 || productEntity.getQuantity() - product.getQuantity() < 0)
                        throw new IllegalStateException(productEntity.getName() + " invalid quantity");
                    productService.updateQuantityByIdAndStatus(
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
        Order order = orderConverter.toEntity(dto);
        order.setOrderDetails(orderDetailsList);
        order.setTotalMoney(totalMoney);
        order.setStatus(SystemConstant.STATUS_ORDER_APPROVE);
        order.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Order finalOrder = orderRepo.save(order);
        orderDetailsList.forEach(orderDetails -> orderDetails.setOrder(finalOrder));

        orderDetailsService.addAllOrderDetail(orderDetailsList);
        OrderResp orderResp = orderConverter.orderResp(finalOrder);
        orderResp.setMessage("Add success");
        orderResp.setProducts(dto.getProducts());
        return orderResp;
    }
}
