package com.amigoscode.examples;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class MinMax {

    @Test
    public void min() {
        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
        int min = numbers.stream().min(Integer::compareTo).get();
        System.out.println(min);
    }

    @Test
    public void max(){
        List<Integer> numbers = List.of(1, 2, 3, 100, 23, 93, 99);
        int max = numbers.stream().max(Comparator.naturalOrder()).get();
        System.out.println(max);
    }
}
