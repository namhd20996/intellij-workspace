package com.example.assign.dto;

import com.example.assign.sysenum.TokenType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class TokenDTO {

    private UUID id;

    private String token;

    private TokenType tokenType;

    private boolean expired;

    private boolean revoked;

    private UserDTO user;
}