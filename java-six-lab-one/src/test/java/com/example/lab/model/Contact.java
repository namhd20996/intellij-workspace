package com.example.lab.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    private String email;

    private String phone;

    private String address;

}
