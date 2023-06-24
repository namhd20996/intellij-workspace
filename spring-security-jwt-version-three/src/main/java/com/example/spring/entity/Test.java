package com.example.spring.entity;

import static com.example.spring.entity.Permission.ADMIN_CREATE;

public class Test {

    public static void main(String[] args) {
        var user = User.builder()
                .role(Role.MANAGER)
                .build();

//        System.out.println(ADMIN_CREATE.getPermission());
//        System.out.println(user.getRole());
        System.out.println(user.getRole().getAuthorities());
    }
}
