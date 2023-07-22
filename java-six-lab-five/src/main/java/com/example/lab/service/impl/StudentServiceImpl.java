package com.example.lab.service.impl;

import com.example.lab.converter.StudentConverter;
import com.example.lab.dto.StudentDTO;
import com.example.lab.entity.Student;
import com.example.lab.repo.StudentRepo;
import com.example.lab.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo repo;

    private final StudentConverter converter;

    @Override
    public StudentDTO addStudent(StudentDTO dto) {
        Student student = repo.findById(dto.getEmail()).orElse(null);
        StudentDTO studentDTO;
        if (student != null) {
            student.setGender(dto.getGender());
            student.setMarks(dto.getMarks());
            student.setFullName(dto.getFullName());
        } else {
            student = converter.toEntity(dto);
        }
        studentDTO = converter.toDTO(repo.save(student));
        return studentDTO;
    }

    @Override
    public void deleteStudent(String email) {
        repo.deleteById(email);
    }

    @Override
    public StudentDTO findOneStudent(String email) {
        return converter.toDTO(repo.findById(email).orElse(null));
    }

    @Override
    public List<StudentDTO> getAll() {
        return converter.toListDTO(repo.findAll());
    }

    @Override
    public boolean existsByEmail(String email) {
        return repo.existsByEmail(email);
    }
}
