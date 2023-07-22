package com.amigoscode.examples;


import com.amigoscode.beans.Car;
import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;
import java.util.stream.Collectors;

public class HowStreamsWork {
    @Test
    public void understandingCollect() throws Exception {
        List<String> emails = MockData.getPeople()
                .stream()
                .map(Person::getEmail)
                .toList();
        System.out.println(emails);
    }

    @Test
    public void intermediateAndTerminalOperations() throws Exception {
        //                            System.out.println("mapping car " + car);
        System.out.println(
                MockData.getCars()
                        .stream()
                        .filter(car -> car.getPrice() < 10000)
                        .map(Car::getPrice)
                        .map(price -> price + (price * .14))
                        .toList()
        );
    }
}
