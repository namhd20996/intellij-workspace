package com.example.assign.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_order")
public class Order {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    @Column
    @CreatedDate
    private Date orderDate;
    @Column
    private Double price;
    @Column
    private Integer quantity;
    @Column
    private String fullName;
    @Column
    private String email;
    @Column
    private String phoneNumber;
    @Column
    private String address;
    @Column
    private String note;
    @Column
    private Integer status;
    @Column
    private Double totalMoney;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @OneToMany(mappedBy = "order")
    private List<OrderDetails> orderDetails = new ArrayList<>();
}
