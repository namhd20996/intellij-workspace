package com.example.lab.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_category")
public class CategoryEntity extends BaseEntity {

    @Column(name = "name")
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;
}
