package com.example.assign.dto;

import com.example.assign.sysenum.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {

    private UUID id;

    private String token;

    private TokenType tokenType;

    private boolean expired;

    private boolean revoked;

    private UserDTO user;
}
