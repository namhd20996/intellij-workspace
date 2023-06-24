package com.stream;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.stream.Gender.FEMALE;
import static com.stream.Gender.MALE;

public class Main {

    public static void main(String[] args) {
        List<Person> peoples = getPeoples();

//        peoples.stream()
//                .filter(person -> FEMALE.equals(person.getGender()))
//                .forEach(System.out::println);

        System.out.println("Sorted");
        // sắp xếp theo nhiều điều kiện khi sắp xếp theo tuổi giống nhau
        // thì sẽ sắp xếp theo giới tính
        peoples.stream()
                .sorted(Comparator.comparing(Person::getAge).reversed()
                        .thenComparing(Person::getGender))
                .forEach(System.out::println);

        System.out.println("Sorted name");
        peoples.stream()
                .sorted(Comparator.comparing(Person::getName))
                .forEach(System.out::println);

        System.out.println("Max age");
        peoples.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        System.out.println("Min age");
        peoples.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        System.out.println("Max age number");
        peoples.stream()
                .mapToInt(Person::getAge)
                .max()
                .ifPresent(System.out::println);

        System.out.println("Group");
        peoples.stream()
                .collect(Collectors.groupingBy(Person::getGender))
                .forEach((gender, people) -> {
                    System.out.println(gender);
                    people.forEach(System.out::println);
                });

        Optional<String> s = peoples.stream()
                .filter(person -> person.getGender().equals(FEMALE))
                .max(Comparator.comparing(Person::getAge))
                .map(Person::getName);
        s.ifPresent(System.out::println);
    }

    private static List<Person> getPeoples() {
        List<Person> peoples = List.of(
                new Person("Duy Nam", 23, MALE),
                new Person("Duy Nam 1", 20, FEMALE),
                new Person("Duy Nam 2", 18, MALE),
                new Person("Duy Nam 3", 27, FEMALE),
                new Person("Duy Nam 4", 50, MALE),
                new Person("Duy Nam 5", 11, MALE)
        );
        return peoples;
    }
}
