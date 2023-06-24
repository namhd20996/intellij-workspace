package com.example.spring.auth;

import com.example.spring.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private List<Role> roles;
}
