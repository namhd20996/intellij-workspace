package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class StatisticsWithStreams {

    @Test
    public void count() throws IOException {
        List<Car> cars = MockData.getCars();
        long count = cars.stream()
                .filter(car -> car.getMake().equalsIgnoreCase("Ford"))
                .filter(car -> car.getYear() > 2010)
                .count();
        System.out.println(count);
    }

    @Test
    public void min() throws IOException {
        List<Car> cars = MockData.getCars();
        double min = cars.stream()
                .mapToDouble(Car::getPrice)
                .min()
                .orElse(0);
        System.out.println(min);
    }

    @Test
    public void max() throws IOException {
        List<Car> cars = MockData.getCars();
        double max = cars.stream()
                .mapToDouble(Car::getPrice)
                .max()
                .orElseGet(() -> 0);
        System.out.println(max);
    }

    @Test
    public void average() throws IOException {
        List<Car> cars = MockData.getCars();
        double average = cars.stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElseGet(() -> 0);
        System.out.println(average);
    }

    @Test
    public void statistics() throws IOException {
        List<Car> cars = MockData.getCars();
        DoubleSummaryStatistics statistics = cars.stream()
                .mapToDouble(Car::getPrice)
                .summaryStatistics();
        System.out.println(statistics);
    }

}
