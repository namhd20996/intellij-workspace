package com.example.assign.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_product")
public class Product extends BaseEntity {

    @Column
    private String name;
    @Column
    private String shortDescription;
    @Column
    private String longDescription;
    @Column
    private Double price;
    @Column
    private Integer quantity;
    @Column
    private Double discount;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "product")
    private List<Gallery> galleries = new ArrayList<>();
    @OneToMany(mappedBy = "product")
    private List<OrderDetails> orderDetails = new ArrayList<>();

}
