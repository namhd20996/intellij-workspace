package com.example.assign.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_category")
public class Category extends BaseEntity {

    @Column
    private String name;
    @Column
    private String description;
    @Column
    private String image;
    @Column
    private Integer status;
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

}
