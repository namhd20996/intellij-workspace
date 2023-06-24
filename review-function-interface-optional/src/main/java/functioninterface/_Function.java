package functioninterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {

    public static void main(String[] args) {

        int resultV1 = incrementFunction.apply(5);
        System.out.println(resultV1);

        Function<Integer, Integer> resultV2 =
                incrementFunction.andThen(multiplyFunction);
        System.out.println(resultV2.apply(5));

        int resultV3 = integerBiFunction.apply(5, 5);
        System.out.println(resultV3);

    }

    static Function<Integer, Integer> incrementFunction =
            number -> number + 1;

    static Function<Integer, Integer> multiplyFunction =
            number -> number * 10;

    static BiFunction<Integer, Integer, Integer> integerBiFunction =
            (a, b) -> (a + 1) * b;


}
