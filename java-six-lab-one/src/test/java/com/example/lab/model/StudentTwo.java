package com.example.lab.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StudentTwo {

    private String name;

    private Boolean gender;

    private Double mark;

    private Contact contact;

    private List<String> subjects;
}
