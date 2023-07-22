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
@Table(name = "_account")
public class AccountsEntity extends BaseEntity {

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "email")
    private String email;
    @Column(name = "photo")
    private String photo;
    @JsonIgnore
    @OneToMany(mappedBy = "accountsEntity")
    private List<OrdersEntity> ordersEntities;
}
