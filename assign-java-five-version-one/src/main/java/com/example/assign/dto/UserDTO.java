package com.example.assign.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseDTO<UserDTO> {
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    private Boolean status;
    private List<RoleDTO> roles = new ArrayList<>();
    private List<TokenDTO> tokens = new ArrayList<>();
    private List<OrderDTO> orders = new ArrayList<>();
    private List<ProductDTO> products = new ArrayList<>();
}
