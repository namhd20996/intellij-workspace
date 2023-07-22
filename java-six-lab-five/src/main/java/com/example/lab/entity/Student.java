package com.example.lab.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_student")
public class Student {

    @Id
    private String email;
    @Nationalized
    @Column(name = "fullname")
    private String fullName;
    @Column(name = "gender")
    private String gender;
    @Column(name = "marks")
    private Double marks;
    @Column(name = "country")
    private String country;

}
