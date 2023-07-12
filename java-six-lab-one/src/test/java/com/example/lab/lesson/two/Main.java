package com.example.lab.lesson.two;

import com.example.lab.model.Student;
import org.junit.jupiter.api.Test;

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
        students
                .forEach(System.out::println);
    }

    @Test
    public void exerciseTwo() {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6, 7, 8);
        List<Double> doubles = numbers.stream()
                .filter(number -> number % 2 == 0)
                .map(Math::sqrt)
                .peek(System.out::println)
                .toList();
    }

    @Test
    public void exerciseThree() {
        // .peek duyệt qua và làm gì đó giống như forEach nhưng nó được sử dụng tiếp nối được còn forEach thì không
        List<Student> list = students.stream()
                .filter(student -> student.getMark() > 7)
                .peek(student -> student.setName(student.getName().toUpperCase()))
                .toList();
        list.forEach(System.out::println);
    }

    @Test
    public void exerciseFour() {
        double average = students.stream()
                .mapToDouble(Student::getMark)
                .average()
                .orElse(0);
        System.out.println("Average: " + average);

        double sum = students.stream()
                .mapToDouble(Student::getMark)
                .sum();
        System.out.println("Sum: " + sum);

        double min = students.stream()
                .mapToDouble(Student::getMark)
                .min()
                .orElse(0);
        System.out.println("Min: " + min);

        double max = students.stream()
                .mapToDouble(Student::getMark)
                .max()
                .orElse(0);
        System.out.println("Max: " + max);

        boolean allMatch = students.stream()
                .allMatch(student -> student.getMark() >= 5);
        System.out.println("allMatch: " + allMatch);

        Student student = students.stream()
                .reduce(students.get(0), (minSv, sv) -> sv.getMark() < minSv.getMark() ? sv : minSv);
        System.out.println(student);
    }
}
