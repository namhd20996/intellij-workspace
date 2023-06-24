package com.example.unit.repo;

import com.example.unit.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepo extends JpaRepository<Student, Long> {

    boolean existsByEmail(String email);

}
