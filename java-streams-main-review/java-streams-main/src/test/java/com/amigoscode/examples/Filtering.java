package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Filtering {

    @Test
    public void filter() throws IOException {
        List<Car> cars = MockData.getCars();
        Predicate<Car> priceCar20k = car -> car.getPrice() < 20_000.00;
        Predicate<Car> colorCar = car -> car.getColor().equals("Yellow");

//        List<Car> carLessThan20kAndColorYellow =
        cars.stream()
                .filter(priceCar20k)
                .filter(colorCar)
                .collect(Collectors.toList())
                .forEach(System.out::println);
//        carLessThan20kAndColorYellow.forEach(System.out::println);
    }

    @Test
    public void dropWhile() {
        System.out.println("Using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12).filter(number -> number % 2 == 0)
                .forEach(n -> System.out.printf("%d, ", n));
        System.out.println();
        System.out.println("Using dropWhile");
        // phương thức dropWhile() sẽ ignore hết tất cả các element thỏa điều kiện từ trái sang
        // nếu nó gặp 1 element nào không thỏa thì nó sẽ trả dữ liệu từ vị trí của element đó đến hết
        Stream.of(2, 4, 6, 8, 9, 10, 12).dropWhile(number -> number % 2 == 0)
                .forEach(n -> System.out.printf("%d, ", n));
    }

    @Test
    public void tabkeWhile() {
        System.out.println("Using filter");
        Stream.of(2, 4, 6, 8, 9, 10, 12).filter(n -> n % 2 == 0)
                .forEach(n -> System.out.printf("%d, ", n));
        System.out.println();
        System.out.println("Using takeWhile");
        // phương thức takeWhile() nó sẽ duyệt các element từ trái sang thỏa điều kiện
        // nếu element nào không thỏa nó sẽ trả ra dữ liệu trước đó mà đã duyệt thỏa
        Stream.of(2, 4, 6, 8, 9, 10, 12).takeWhile(n -> n % 2 == 0)
                .forEach(n -> System.out.printf("%d, ", n));
    }

    @Test
    public void findFirst() throws IOException {
        // findFirst() tìm ra value của element đầu tiên nó gặp trả ra
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 15};
        int result = Arrays.stream(numbers).filter(n -> n == 15)
                .findFirst()
                .orElse(-1);
        System.out.println(result);

        // Khi không có điều kiện nó trả ra value đầu tiên
        int result2 = Arrays.stream(numbers)
                .findFirst()
                .orElse(-1);
        System.out.println(result2);
    }

    @Test
    public void findAny() throws IOException {
        // findAny() Trả ra value bất kể element nào khi thỏa điều kiện không quan tâm đến thứ tự của kết quả
        int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 10, 15};
        int result = Arrays.stream(numbers).filter(n -> n == 9)
                .findAny()
                .orElse(-1);
        System.out.println(result);
        // Khi không có điều kiện nó trả ra value đầu tiên
        int result2 = Arrays.stream(numbers)
                .findAny()
                .orElse(-1);
        System.out.println(result2);
    }

    @Test
    public void allMatch() throws IOException {
        int[] numbers = {2, 4, 6, 8, 9};
        boolean result = Arrays.stream(numbers).allMatch(n -> n % 2 == 0);
        System.out.println(result);
    }

    @Test
    public void anyMatch() throws IOException{
        int[] numbers = {2, 4, 6, 8, 9};
        boolean result = Arrays.stream(numbers).anyMatch(n -> n % 2 == 0);
        System.out.println(result);
    }

    @Test
    public void noneMatch() throws IOException{
        int[] numbers = {2, 4, 6, 8, 9};
        boolean result = Arrays.stream(numbers).noneMatch(n -> n % 2 == 0);
        System.out.println(result);
    }
}
