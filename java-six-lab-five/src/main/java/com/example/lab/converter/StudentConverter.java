package com.example.lab.converter;

import com.example.lab.dto.StudentDTO;
import com.example.lab.entity.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StudentConverter {

    private final ModelMapper mapper;

    public Student toEntity(StudentDTO dto) {
        return Optional.ofNullable(dto)
                .map(student -> mapper.map(student, Student.class))
                .orElse(null);
    }

    public StudentDTO toDTO(Student entity) {
        return Optional.ofNullable(entity)
                .map(student -> mapper.map(student, StudentDTO.class))
                .orElse(null);
    }

    public List<StudentDTO> toListDTO(List<Student> list) {
        return Optional.of(list)
                .map(students -> List.of(mapper.map(students, StudentDTO[].class)))
                .orElse(null);
    }
}
