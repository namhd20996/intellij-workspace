package com.example.assign.entity;

import jakarta.persistence.*;
import lombok.*;



@Getter
@Setter
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
