package com.example.lab.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {

    private String email;
    @NotNull(message = "Full name not null")
    private String fullName;
    private String gender;
    private Double marks;
    private String message;
    @NotNull(message = "Country not null")
    private String country;
}
