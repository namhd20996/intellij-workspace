package com.example.assign.order;

import com.example.assign.constant.SystemConstant;
import com.example.assign.exception.ApiRequestException;
import com.example.assign.orderdetails.OrderDetails;
import com.example.assign.orderdetails.OrderDetailsService;
import com.example.assign.product.ProductDTO;
import com.example.assign.product.ProductDTOMapper;
import com.example.assign.product.ProductService;
import com.example.assign.user.User;
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

    private final OrderDTOMapper orderDTOMapper;

    private final ProductDTOMapper productDTOMapper;

    private final ProductService productService;

    private final OrderDetailsService orderDetailsService;


    @Override
    public List<OrderDTO> findAllByIdAndStatus(UUID id, Integer status) {
        return orderRepo.findAllByIdAndStatus(id, status)
                .stream()
                .map(orderDTOMapper::toDTO)
                .toList();
    }

    @Override
    public List<OrderDTO> findAll() {
        return orderRepo.findAll()
                .stream()
                .map(orderDTOMapper::toDTO)
                .toList();
    }

    @Override
    public OrderDTO findOrderById(UUID id) {
        return orderRepo.findById(id)
                .map(orderDTOMapper::toDTO)
                .orElseThrow(() -> new ApiRequestException("Order id: " + id + "not found!.."));
    }

    @Override
    public void addOrder(OrderAddRequest request) {
        List<ProductDTO> productDTOS = request.getProducts()
                .stream()
                .peek(p -> {
                    ProductDTO product = getProductDTO(p);
                    if (product.getQuantity() == 0 || product.getQuantity() - p.getQuantity() < 0)
                        throw new ApiRequestException(product.getName() + " invalid quantity");
                }).toList();

        List<OrderDetails> orderDetails = productDTOS.stream()
                .map(p -> {
                    ProductDTO product = getProductDTO(p);
                    productService.updateQuantityByIdAndStatus(
                            product.getQuantity() - p.getQuantity(),
                            product.getId(),
                            SystemConstant.STATUS_PRODUCT
                    );
                    return OrderDetails.builder()
                            .product(productDTOMapper.toEntity(product))
                            .price(product.getPrice())
                            .quantity(product.getQuantity())
                            .totalMoney(product.getPrice() * product.getQuantity())
                            .build();
                }).toList();


        double totalMoney = orderDetails.stream()
                .mapToDouble(p -> p.getPrice() * p.getQuantity())
                .sum();

        var order = Order.builder()
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .email(request.getEmail())
                .totalMoney(totalMoney)
                .status(SystemConstant.STATUS_ORDER_APPROVE)
                .user((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .build();

        var finalOrder = orderRepo.save(order);
        orderDetails.forEach(orderDetail -> orderDetail.setOrder(finalOrder));
        orderDetailsService.addAllOrderDetail(orderDetails);
    }

    private ProductDTO getProductDTO(ProductDTO p) {
        return productService.findProductByIdAndStatus(p.getId(), SystemConstant.STATUS_PRODUCT);
    }
}
