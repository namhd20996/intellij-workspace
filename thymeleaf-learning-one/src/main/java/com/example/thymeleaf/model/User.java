package com.example.thymeleaf.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    private String username;

    private String password;

    private String role;

    private String fullname;

    private Date dob;
}
