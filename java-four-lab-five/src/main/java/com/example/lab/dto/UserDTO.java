package com.example.lab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String password;
    private String fullname;
    private String email;
    private Boolean admin = false;
}
