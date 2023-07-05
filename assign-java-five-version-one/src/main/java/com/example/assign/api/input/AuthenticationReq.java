package com.example.assign.api.input;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthenticationReq {

    private String username;

    private String password;
}
