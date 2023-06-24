package com.amigoscode.examples;

import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class IntStreams {

    @Test
    public void range() throws IOException {
        System.out.println("With for i");
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }

        System.out.println("With IntStream");
        IntStream.range(0, 10).forEach(System.out::println);

        System.out.println("With IntStreamClose");
        // <> for(int i=0; i<=10; i++)
        IntStream.rangeClosed(0, 10).forEach(System.out::println);
    }

    @Test
    public void rangeIteratingLists() throws IOException {
        List<Person> peoples = MockData.getPeople();
        IntStream.range(0, peoples.size())
                .forEach(index -> {
                    System.out.println(peoples.get(index));
                });
    }

    @Test
    public void intStreamIterate() {
//        int i = 0;
//        while (true) {
//            i++;
//            System.out.println(i);
//        }

        IntStream.iterate(0, value -> value + 1)
                .limit(10)
                .forEach(System.out::println);
    }
}
