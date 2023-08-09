package com.example.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    private String id;

    private String name;

    private Float price;

    private Integer year;

    private String category;
}
