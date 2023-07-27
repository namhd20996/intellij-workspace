package com.example.assign.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO extends BaseDTO<UserDTO> {
    private String username;
    private String password;
    private String email;
    private String firstname;
    private String lastname;
    @JsonIgnore
    private Integer status;
    private String avatar;
    @JsonIgnore
    private List<RoleDTO> roles = new ArrayList<>();
    @JsonIgnore
    private List<TokenDTO> tokens = new ArrayList<>();
    @JsonIgnore
    private List<OrderDTO> orders = new ArrayList<>();
    @JsonIgnore
    private List<ProductDTO> products = new ArrayList<>();
}
