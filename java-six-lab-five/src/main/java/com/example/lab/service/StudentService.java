package com.example.lab.service;

import com.example.lab.dto.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO addStudent(StudentDTO dto);

    void deleteStudent(String email);

    StudentDTO findOneStudent(String email);

    List<StudentDTO> getAll();

    boolean existsByEmail(String email);
}
