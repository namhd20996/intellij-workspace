package com.example.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "employee")
public class EmployeeEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column
    private String name;
    @Column
    private String email;
    @Column(name = "jobtitle")
    private String jobTitle;
    @Column
    private String phone;
    @Column(name = "imageurl")
    private String imageUrl;
    @Column(name = "employeecode", nullable = false, updatable = false)
    private String employeeCode;
}
