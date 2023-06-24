package com.example.spring.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "_role")
public class Role extends BaseEntity {

    @Column
    private String name;
    @Column
    private String code;
    public Role(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
