package com.example.lab.resource;

import com.example.lab.dto.StudentDTO;
import com.example.lab.exception.ApiRequestException;
import com.example.lab.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/student")
@RequiredArgsConstructor
public class StudentResource {

    private final StudentService service;

    @GetMapping("/all")
    public ResponseEntity<List<StudentDTO>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/find/{email}")
    public ResponseEntity<StudentDTO> findStudent(@PathVariable("email") String email) {
        return new ResponseEntity<>(service.findOneStudent(email), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@Validated @RequestBody StudentDTO student, Errors errors) {
        if (service.existsByEmail(student.getEmail())) {
            throw new ApiRequestException("Email is taken...");
        }
        if (errors.hasErrors()) {
            List<String> list = errors.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();
            throw new ApiRequestException(list.toString());
        }
        return new ResponseEntity<>(service.addStudent(student), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentDTO> updateStudent(@RequestBody StudentDTO student) {
        return new ResponseEntity<>(service.addStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("{email}")
    public ResponseEntity<?> deleteStudent(@PathVariable("email") String email) {
        service.deleteStudent(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
