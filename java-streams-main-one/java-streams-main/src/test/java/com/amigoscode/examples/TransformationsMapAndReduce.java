package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.beans.PersonDTO;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransformationsMapAndReduce {

    @Test
    void yourFirstTransformationWithMap() throws IOException {
        List<Person> people = MockData.getPeople();
        people.stream()
                .filter(p -> p.getAge() > 20)
                .map(PersonDTO::map)
                .toList()
                .forEach(System.out::println);
    }

    @Test
    void mapToDoubleAndFindAverageCarPrice() throws IOException {
        List<Car> cars = MockData.getCars();
        double average = cars.stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);
        System.out.println(average);
    }

    @Test
    public void reduce() {
        int[] integers = {1, 2, 3, 4, 99, 100, 121, 1302, 199};
        int reduce = Arrays.stream(integers).reduce(0, Integer::sum);
        System.out.println(reduce);
        int reduceOne = Arrays.stream(integers).reduce(0, (a, b) -> a - b);
        System.out.println(reduceOne);
    }
}

