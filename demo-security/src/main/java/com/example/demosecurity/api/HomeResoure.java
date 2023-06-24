package com.example.demosecurity.api;

import com.example.demosecurity.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/student")
public class HomeResoure {

    private static final List<Student> STUDENTS = Arrays.asList(new Student(1, "Teo"),
            new Student(2, "Teo 2"),
            new Student(3, "Teo 2")
    );

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Integer id) {
        return new ResponseEntity<>(STUDENTS.stream()
                .filter(student -> id.equals(student.getId()))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Student " + id + " not exists")), HttpStatus.OK);
    }
}
