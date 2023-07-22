package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Sorting {

    @Test
    public void sortingSteamOfElements() throws IOException {
        List<Person> peoples = MockData.getPeople();
        peoples.stream()
                .map(Person::getFirstName)
                .sorted()
                .toList()
                .forEach(System.out::println);
    }

    @Test
    public void sortingSteamOfElementsReverse() throws IOException {
        List<Person> peoples = MockData.getPeople();
        peoples.stream()
                .map(Person::getFirstName)
                .sorted(Comparator.reverseOrder())
                .toList()
                .forEach(System.out::println);
    }

    @Test
    public void sortingSteamOfObjets() throws IOException {
        List<Person> people = MockData.getPeople();
        Comparator<Person> comparing = Comparator
                .comparing(Person::getFirstName)
                .reversed()
                .thenComparing(Person::getEmail);

        people.stream()
                .sorted(comparing)
                .toList()
                .forEach(System.out::println);
    }

    @Test
    public void topTenMostExpensiveBlueCars() throws IOException {
        List<Car> cars = MockData.getCars();
        cars.stream()
                .filter(c -> c.getColor().equalsIgnoreCase("blue"))
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .limit(10)
                .toList()
                .forEach(System.out::println);
    }

}
