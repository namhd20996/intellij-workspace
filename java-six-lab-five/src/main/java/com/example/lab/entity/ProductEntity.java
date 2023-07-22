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
@Table(name = "_product")
public class ProductEntity extends BaseEntity {

    @Column(name = "name")
    private String name;
    @Column(name = "image")
    private String image;
    @Column(name = "price")
    private Double price;
    @Column(name = "available")
    private Boolean available;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderDetailsEntity> orderDetailsEntities;
}
