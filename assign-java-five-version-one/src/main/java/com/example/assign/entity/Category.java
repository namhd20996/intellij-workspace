package com.example.assign.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_category")
public class Category extends BaseEntity {

    @Column
    @Nationalized
    private String name;
    @Column
    @Nationalized
    private String description;
    @Column
    private String image;
    @Column
    private Integer status;
    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();

}
