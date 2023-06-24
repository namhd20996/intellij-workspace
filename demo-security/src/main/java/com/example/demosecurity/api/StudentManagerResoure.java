package com.example.demosecurity.api;

import com.example.demosecurity.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class StudentManagerResoure {

    private static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "Teo"),
            new Student(2, "Teo 2"),
            new Student(3, "Teo 2")
    );

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")
    public ResponseEntity<List<Student>> getAllStudent() {
        return new ResponseEntity<>(STUDENTS, HttpStatus.OK);
    }

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<Student> insertStudent(@RequestBody Student student) {
        System.out.println(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Integer id) {
        System.out.println(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
