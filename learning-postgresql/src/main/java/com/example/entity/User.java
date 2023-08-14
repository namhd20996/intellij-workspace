package com.example.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "_book")
public class Book {

    @Id
    private String id;

    private String name;

    private Float price;

    private Integer year;

    private String category;

}
