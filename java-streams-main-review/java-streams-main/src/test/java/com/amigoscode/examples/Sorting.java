package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Sorting {

    @Test
    public void sortingSteamOfElements() throws IOException {
        List<Person> peoples = MockData.getPeople();
        peoples.stream()
                .map(Person::getFirstName)
                .sorted()
                .forEach(System.out::println);
    }

    @Test
    public void sortingStreamOfElementsReverse() throws IOException {
        List<Person> peoples = MockData.getPeople();
        peoples.stream()
                .map(Person::getFirstName)
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);
    }

    @Test
    public void sortingStreamOfObject() throws IOException {
        List<Person> peoples = MockData.getPeople();
        peoples.stream()
                .sorted(Comparator.comparing(Person::getEmail).reversed()
                        .thenComparing(Person::getFirstName))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    public void topTenMostExpensiveBlueCars() throws IOException {
        List<Car> cars = MockData.getCars();
        cars.stream()
                .filter(car -> car.getColor().equalsIgnoreCase("blue"))
                .sorted(Comparator.comparing(Car::getPrice).reversed())
                .limit(10)
                .forEach(System.out::println);
    }
}


