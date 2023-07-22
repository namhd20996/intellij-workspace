package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;



@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_role")
public class Role extends BaseEntity {

    @Column
    private String name;
    @Column
    private String code;
}
