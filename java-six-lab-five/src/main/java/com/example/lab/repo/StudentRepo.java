package com.example.lab.repo;

import com.example.lab.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, String> {
    boolean existsByEmail(String email);

    Optional<Student> findStudentByEmail(String email);
}
