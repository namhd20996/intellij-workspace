package streams;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import static streams._Stream.Gender.FEMALE;
import static streams._Stream.Gender.MALE;


public class _Stream {

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("John", MALE),
                new Person("Maria", FEMALE),
                new Person("Aisha", FEMALE),
                new Person("Alex", MALE),
                new Person("Alice", FEMALE)
        );

        // .forEach(gender -> System.out.println(gender)); tương đương
        // .forEach(System.out::println);
        people.stream()
                .map(person -> person.gender)
                .collect(Collectors.toSet())
                .forEach(System.out::println);

        // .mapToInt(name-> name.length()) tương đường
        // .mapToInt(String::length)
        people.stream()
                .map(person -> person.name)
                .mapToInt(String::length)
                .forEach(System.out::println);


        Function<Person, String> personStringFunction = person -> person.name;
        ToIntFunction<String> intFunction = String::length;
        IntConsumer intConsumer = System.out::println;
        people.stream()
                .map(personStringFunction)
                .mapToInt(intFunction)
                .forEach(intConsumer);

        // allMatch tất cả element khớp thì trả về true thì mới true giống như toán tử &&
       Boolean containsFemale = people.stream()
                .allMatch(person -> FEMALE.equals(person.gender));

        System.out.println(containsFemale);

        // anyMatch có ít nhất 1 element khớp trả về true giống toán tử ||
        Boolean conBoolean = people.stream()
                                    .anyMatch(person -> FEMALE.equals(person.gender));
        System.out.println(conBoolean);

        // noneMatch không có element nào khớp sẽ trả về true
        Boolean aBoolean = people.stream()
                .noneMatch(person -> MALE.equals(person.gender));
        System.out.println(aBoolean);

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
