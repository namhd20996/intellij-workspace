package com.example.lab.lesson.one;

import com.example.lab.model.FunctionInterfaceDemo;
import com.example.lab.model.Student;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class Main {

    public static final List<Student> students = List.of(
            new Student("Tommy", true, 8.0),
            new Student("Tommy 1", true, 7.0),
            new Student("Tommy 2", true, 10.0),
            new Student("Tommy 3", true, 8.1),
            new Student("Tommy 4", true, 6.0),
            new Student("Tommy 5", true, 8.8)
    );

    @Test
    public void exerciseOne() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        numbers.forEach(System.out::println);
    }

    @Test
    public void exerciseTwo() {
        students.forEach(student -> {
            System.out.println(student.getName());
            System.out.println(student.getMark());
            System.out.println();
        });
    }

    @Test
    public void exerciseThree(){
        students.stream()
                .sorted(Comparator.comparing(Student::getMark))
                .forEach(System.out::println);
    }

    @Test
    public void exerciseFour(){
        FunctionInterfaceDemo demo = System.out::println;
        demo.m1(2023);
    }
}
