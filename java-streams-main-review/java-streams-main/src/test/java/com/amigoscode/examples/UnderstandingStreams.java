package com.amigoscode.examples;

import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UnderstandingStreams {

    @Test
    public void collect() throws IOException {
        List<String> emails = MockData.getPeople().stream()
                .map(Person::getEmail)
                .collect(() -> new ArrayList<String>(),
                        (strings, s) -> {
// strings ở đây là 1 array rỗng, s là email được add vào array
//                            System.out.println(strings);
                            strings.add(s);
                        },
                        (strings, strings2) -> {
                            // làm phẳng array nếu trong array có array con
                            System.out.println(strings2);
                            strings.addAll(strings2);
                        }
                );
//        emails.forEach(System.out::println);
    }
}
