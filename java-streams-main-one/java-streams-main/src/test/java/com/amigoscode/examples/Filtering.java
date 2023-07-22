package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filtering {

    @Test
    public void filter() throws Exception {
        List<Car> cars = MockData.getCars();
        cars.stream()
                .filter(car -> car.getPrice() < 20_000.00)
                .filter(car -> car.getColor().equals("Yellow"))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    @Test
    public void dropWhile() throws Exception {
        System.out.println("Using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .filter(n -> n % 2 == 0)
                .forEach(s -> System.out.printf("%d, ", s));
        System.out.println("\nUsing dropWhile");
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .dropWhile(n -> n % 2 == 0)
                .forEach(s -> System.out.printf("%d, ", s));
    }

    @Test
    public void takeWhile() throws Exception {
        Stream.of(2, 4, 6, 8, 9, 10, 12)
                .takeWhile(n -> n % 2 == 0)
                .forEach(s -> System.out.printf("%d, ", s));
    }

    @Test
    public void findFirst() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 15};
        int result = Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .findFirst()
                .orElse(0);
        System.out.println(result);
    }

    @Test
    public void findAny() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 15};
        int result = Arrays.stream(numbers)
                .filter(n -> n == 9)
                .findAny()
                .orElse(-1);
        System.out.println(result);
    }

    @Test
    public void allMatch() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 15};
        boolean result = Arrays.stream(numbers)
                .allMatch(n -> n % 2 == 0);
        System.out.println(result);
    }

    @Test
    public void anyMatch() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 15};
        boolean result = Arrays.stream(numbers)
                .anyMatch(n -> n % 2 == 0);
        System.out.println(result);
    }

    @Test
    public void noneMatch() throws Exception {
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 15};
        boolean result = Arrays.stream(numbers).noneMatch(n -> n % 11 == 0);
        System.out.println(result);
    }

}



