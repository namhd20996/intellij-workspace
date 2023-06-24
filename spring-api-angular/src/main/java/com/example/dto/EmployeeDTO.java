package com.example.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class EmployeeDTO {

    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private String phone;
    private String imageUrl;
    private String employeeCode;
}
