package com.example.lab.lesson.three;

import com.example.lab.model.Contact;
import com.example.lab.model.StudentTwo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    private final String path = "D:\\intellij-workspace\\java-six-lab-one\\src\\main\\resources\\student.json";
    private final String paths = "D:\\intellij-workspace\\java-six-lab-one\\src\\main\\resources\\students.json";
    private ObjectMapper mapper;

    @BeforeEach
    public void run() {
        mapper = new ObjectMapper();
    }

    @AfterEach
    public void close() {
        mapper = null;
    }

    @Test
    public void exerciseOne() throws IOException {
        JsonNode student = mapper.readTree(new File(path));
        System.out.println("Name: " + student.get("name").asText());
        System.out.println("Gender: " + (student.get("gender").asBoolean() ? "Nam" : "Nữ"));
        System.out.println("Mark: " + student.get("mark").asDouble());
        System.out.println("Email: " + student.get("contact").get("email").asText());
        System.out.println("Phone: " + student.findValue("phone").asText());
        student.get("subjects").iterator()
                .forEachRemaining(s -> System.out.println("Subjects: " + s.asText()));
    }

    @Test
    public void exerciseTwo() throws IOException {
        Map<String, Object> student = mapper.readValue(new File(path), Map.class);
        System.out.println("Name: " + student.get("name"));
        System.out.println("Gender: " + student.get("gender"));
        System.out.println("Mark: " + student.get("mark"));
        Map<String, Object> contact = (Map<String, Object>) student.get("contact");
        System.out.println("Email: " + contact.get("email"));
        System.out.println("Phone: " + contact.get("phone"));
        List<String> subjects = (List<String>) student.get("subjects");
        subjects.forEach(x -> System.out.print("\tSubjects: " + x));
    }

    @Test
    public void exerciseThree() throws IOException {
        List<Map<String, Object>> students = mapper.readValue(new File(paths), List.class);
        students.forEach(s -> System.out.println(s.get("name")));
    }

    @Test
    public void exerciseFour() throws IOException {
        StudentTwo studentTwo = mapper.readValue(new File(path), StudentTwo.class);
        System.out.println("Name: " + studentTwo.getName());
        System.out.println("Gender: " + studentTwo.getGender());
        System.out.println("Mark: " + studentTwo.getMark());
        Contact contact = studentTwo.getContact();
        System.out.println("Email: " + contact.getEmail());
        System.out.println("Phone: " + contact.getPhone());
        List<String> subjects = studentTwo.getSubjects();
        subjects.forEach(x -> System.out.print("\tSubjects: " + x));
    }

    @Test
    public void exerciseFive() throws IOException {
        TypeReference<List<StudentTwo>> types = new TypeReference<>() {
        };
        List<StudentTwo> studentTwos = mapper.readValue(new File(paths), types);
        studentTwos.forEach(s -> System.out.println(s.getName()));
    }

    @Test
    public void exerciseSix() throws IOException {
        ObjectNode node = mapper.createObjectNode();
        node.put("name", "Hoàng Duy Nam");
        node.put("mark", 8.8);
        node.put("gender", true);
        ObjectNode contact = node.putObject("contact");
        contact.put("email", "hoangduynam20996@gmail.com");
        contact.put("phone", "0968997434");
        ArrayNode subjects = node.putArray("subjects");
        subjects.add("WEB205");
        subjects.add("COM108");

        // Write to String
        String json = mapper.writeValueAsString(node);
        // Write to output
        mapper.writeValue(System.out, node);
        // Write to file
        mapper.writeValue(new File("D:/temp/student.txt"), node);
    }

    @Test
    public void exerciseSeven() throws IOException {
        Map<String, Object> contact = new HashMap<>();
        contact.put("email", "hoangduynam20996@gmail.com");
        contact.put("phone", "0968997434");
        List<String> subjects = List.of("WEB205", "COM108");
        Map<String, Object> student = new HashMap<>();
        student.put("name", "Hoàng Duy Nam");
        student.put("mark", 8.8);
        student.put("gender", true);
        student.put("contact", contact);
        student.put("subjects", subjects);

        // Write to json
        String json = mapper.writeValueAsString(student);
        // Write to output
        mapper.writeValue(System.out, student);
        // Write to file
        mapper.writeValue(new File("D:/temp/student-map.txt"), student);
    }

    @Test
    public void exerciseEight() throws IOException {
        Contact contact = new Contact("hoangduynam20996@gmail.com", "0968997434", "Bàu bàng");
        List<String> subjects = List.of("WEB205", "COM108");
        StudentTwo studentTwo = new StudentTwo("Hoàng Duy Nam", true, 8.8, contact, subjects);

        // Write to json
        String json = mapper.writeValueAsString(studentTwo);
        // Write to output
        mapper.writeValue(System.out, studentTwo);
        // Write to file
        mapper.writeValue(new File("D:/temp/student-object.txt"), studentTwo);
    }
}
