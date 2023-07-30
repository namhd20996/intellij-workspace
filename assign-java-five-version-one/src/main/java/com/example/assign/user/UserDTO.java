package com.example.assign.user;

import com.example.assign.role.RoleDTO;
import com.example.assign.util.BaseDTO;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO extends BaseDTO<UserDTO> {
    private String email;
    private String firstname;
    private String lastname;
    private String avatar;
    private String address;
    private String token;
    private List<RoleDTO> roles = new ArrayList<>();
}
