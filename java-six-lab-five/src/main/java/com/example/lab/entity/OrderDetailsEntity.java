package com.example.lab.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "_order_details")
public class OrderDetailsEntity extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;
    @ManyToOne
    @JoinColumn(name = "orders_id")
    private OrdersEntity ordersEntity;

    private Double price;

    private Integer quantity;
}
