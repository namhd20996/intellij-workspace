package com.example.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String email;
    private String fullName;
    private Double average;
    private Boolean gender;
    private String country;
}
