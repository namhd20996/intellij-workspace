package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.beans.PersonDTO;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class TransformationsMapAndReduce {

    @Test
    public void yourFirstTransformationWithMap() throws IOException {
        List<Person> peoples = MockData.getPeople();
        
        Function<Person, PersonDTO> functionDTO = person ->
                new PersonDTO(person.getId(),
                        person.getFirstName(),
                        person.getAge());

        List<PersonDTO> personDTOS = peoples.stream()
                .filter(person -> person.getAge() > 20)
                .map(functionDTO)
                .collect(Collectors.toList());
        System.out.println(personDTOS.size());
        System.out.println(peoples.size());
        personDTOS.forEach(System.out::println);
    }

    @Test
    public void mapToDoubleAndFindAverageCarPrice() throws IOException {
        List<Car> cars = MockData.getCars();
        double avg = cars.stream()
                .mapToDouble(Car::getPrice)
                .average()
                .orElse(0);
        System.out.println(avg);
    }

    @Test
    public void reduce() {
        int[] numbers = {1, 2, 3, 4, 99, 100, 121, 1302, 199};
        int[] numberss = {1, 2, 3, 4, 99, 100, 121, 1302, 199};
        int sum = Arrays.stream(numbers).reduce(0, Integer::sum);
        int sub = Arrays.stream(numberss).reduce(0, (a, b) -> a - b);
        System.out.println(sum);
        System.out.println(sub);

        String[] strings = {"1", "2", "3", "4", "5"};
        String result = Arrays.stream(strings).reduce("", (a, b) -> a + b);
        System.out.println(result);


        // range hoạt động tuần tự như vòng lặp for
        // for(int i=1; i<10; i++)
        IntStream
                .range(1, 10)
                .forEach(System.out::println);

        // thống kê số luọng element, tính tổng, min, max, average trong array
        IntSummaryStatistics summaryStatistics = IntStream
                .of(1, 2, 3, 4, 99, 100, 121, 1302, 199)
                .summaryStatistics();
        System.out.println(summaryStatistics);
    }
}
