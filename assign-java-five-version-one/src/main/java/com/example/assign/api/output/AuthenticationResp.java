package com.example.assign.api.output;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResp {

    private String username;
    private String email;
    private String firstname;
    private String lastname;
    private String avatar;
    private String token;
    private String message;
}
