package optionals;

import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Optional.ofNullable("Not null")
                .ifPresent(System.out::println);
    }
}
