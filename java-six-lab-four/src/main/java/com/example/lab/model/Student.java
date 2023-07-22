package com.example.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    private String email;

    private String fullname;

    private Double marks;

    private String gender;

    private String country;
}
