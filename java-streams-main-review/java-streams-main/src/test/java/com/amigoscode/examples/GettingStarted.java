package com.amigoscode.examples;

import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GettingStarted {

    @Test
    public void imperativeApproach() throws IOException {
        // 1. Find people aged less or equal 18
        // 2.Then change implementation to find first 10 people
        List<Person> peoples = MockData.getPeople();
        List<Person> youngPeople = new ArrayList<>();
        int count = 0;
        int limit = 10;
        for (Person person : peoples) {
            if (person.getAge() <= 18) {
                youngPeople.add(person);
                count++;
                if (count == limit) {
                    break;
                }
            }
        }
        for (Person person : youngPeople) {
            System.out.println(person);
        }
    }

    @Test
    public void declaretiveApproachUsingStreams() throws IOException {
        List<Person> peoples = MockData.getPeople();
        List<Person> youngPeople = peoples.stream()
                .filter(person -> person.getAge() <= 18)
                .limit(10)
                .collect(Collectors.toList());
        youngPeople.forEach(System.out::println);
    }
}
