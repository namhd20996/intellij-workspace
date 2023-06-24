package com.amigoscode.examples;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class WorkingWithStreams {

    @Test
    @Disabled
    void streams() {
        List<String> names = List.of("Duy Nam", "Kiều Nga", "Kiều Diễm");
        Stream<String> stream = names.stream();
        Stream<String> namesStream = Stream.of("Duy Nam", "Kiều Nga", "Kiều Diễm");

        // Có thể sử dụng nhiều tóan tử trung gian
        long count = stream
                .limit(2).map(null).sorted(null).dropWhile(null)
                .count();
        // Khi muốn chuyển đổi 1 Array -> stream
        String[] arrays = {};
        Arrays.stream(arrays);
    }
}
