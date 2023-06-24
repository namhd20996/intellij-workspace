package com.example.unit;

import com.example.unit.entity.Gender;
import com.example.unit.entity.Student;
import com.example.unit.repo.StudentRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UnitTestLearningOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(UnitTestLearningOneApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentRepo repo){
        return args -> {
            String email = "duynam@gmail.com";
            Student student = new Student(
                    "Duy Nam",
                    email,
                    Gender.MALE
            );
            repo.save(student);
        };
    }
}
