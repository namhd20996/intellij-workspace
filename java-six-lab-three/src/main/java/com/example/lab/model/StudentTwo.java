package com.example.lab.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentTwo {
    @NotBlank(message = "Không để trống email")
    @Email(message = "Không đúng định dạng email")
    private String email;
    @NotBlank(message = "Không để trống fullname")
    private String fullName;
    @NotNull(message = "Không để trống điểm")
    @PositiveOrZero(message = "Điểm phải lớn hơn hoặc bằng 0")
    @Max(value = 10, message = "Điểm phải nhỏ hơn hoặc bằng 10")
    private Double average;
    @NotNull(message = "Không để trống gender")
    private Boolean gender;
    @NotBlank(message = "Không để trống coutry")
    private String country;
}
