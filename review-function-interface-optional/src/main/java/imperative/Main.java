package imperative;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static imperative.Main.Gender.FEMALE;
import static imperative.Main.Gender.MALE;

public class Main {

    public static void main(String[] args) {
        List<Person> peoples = Arrays.asList(
                new Person("Linda", MALE),
                new Person("Alice", FEMALE),
                new Person("Tom", MALE),
                new Person("Tommy", MALE),
                new Person("Ana", FEMALE)
        );

        Predicate<Person> predicate = person -> FEMALE.equals(person.gender);
        peoples.stream()
                .filter(predicate)
                .forEach(System.out::println);

        peoples.stream()
                .map(person->person)
                .forEach(System.out::println);

        peoples.stream()
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
        MALE,
        FEMALE
    }
}
