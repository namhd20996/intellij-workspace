package com.example.unit.repo;

import com.example.unit.entity.Gender;
import com.example.unit.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepoTest {

    @Autowired
    private StudentRepo repo;

    @Test
    void itShouldCheckIfStudentExists() {
        // given
        String email = "duynam@gmail.com";
        Student student = new Student(
                "Duy Nam",
                email,
                Gender.MALE
        );
        repo.save(student);
        // when
        boolean exists = repo.existsByEmail(email);
        // then
        assertThat(exists).isTrue();
    }
}