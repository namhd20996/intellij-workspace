package com.example.lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_orders")
public class OrdersEntity extends BaseEntity {

    @Column(name = "username")
    private String username;
    @Column(name = "address")
    private String address;
    @JsonIgnore
    @OneToMany(mappedBy = "ordersEntity")
    private List<OrderDetailsEntity> orderDetailsEntities;
    @JoinColumn(name = "account_id")
    @ManyToOne
    private AccountsEntity accountsEntity;
}
