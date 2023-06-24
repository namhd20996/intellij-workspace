package functioninterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {

    public static void main(String[] args) {

        stringConsumer.accept("Hello! Welcome: Consumer Function");

        biConsumer.accept("Nam", true);
        biConsumer.accept("Nam", false);

    }

    static Consumer<String> stringConsumer =
            System.out::println;

    static BiConsumer<String, Boolean> biConsumer =
            (str, boo) -> System.out.println("Hello! Welcome: "
                    + str + (boo ? ", thank for listening " : ""));
}
