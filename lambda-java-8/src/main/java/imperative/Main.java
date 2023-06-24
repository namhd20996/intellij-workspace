package imperative;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static imperative.Main.Gender.*;

public class Main {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("John", MALE),
                new Person("Maria", FEMALE),
                new Person("Aisha", FEMALE),
                new Person("Alex", MALE),
                new Person("Alice", FEMALE)
        );

        System.out.println("Imperative approach");
        // Imperative approach
        List<Person> females = new ArrayList<Person>();
        for (Person person : people) {
            if (FEMALE.equals(person.gender)) {
                females.add(person);
            }
        }

        for (Person person : females) {
            System.out.println(person);
        }

        // stream() nó trả về 1 tập hợp tuần tự

        Predicate<Person> isPerson = person -> FEMALE.equals(person.gender);

        people.forEach(item -> System.out.println(item.gender));
        System.out.println("Declarative approach");
        people.stream()
                .filter(isPerson)
                .collect(Collectors.toList())
                .forEach(System.out::println);

    }

    static class Person {
        private final String name;
        private final Gender gender;

        public Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender {
        MALE, FEMALE
    }
}
