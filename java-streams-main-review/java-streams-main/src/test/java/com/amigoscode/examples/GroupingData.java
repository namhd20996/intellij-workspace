package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupingData {

    @Test
    public void simpleGroup() throws IOException {
        List<Car> cars = MockData.getCars();
        Map<String, List<Car>> collect = cars.stream()
                .collect(Collectors.groupingBy(Car::getMake));

        collect.forEach((make, car) -> {
            System.out.println(make);
            car.forEach(System.out::println);
        });
    }

    @Test
    public void groupingAndCounting() throws IOException {
        List<String> strings = List.of(
                "John",
                "John",
                "Mariam",
                "Alex",
                "Mohammado",
                "Mohammado",
                "Vincent",
                "Alex",
                "Alex"
        );

        Map<String, Long> map = strings.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()));
        System.out.println(map);
    }
}
