package functionalinterface;

import java.util.function.BiFunction;
import java.util.function.Function;

public class _Function {

    public static void main(String[] args) {
        //  function normal java
        int increment = increment(1);
        System.out.println("Number one: " + increment);

        // Function interface
        int increment2 = incrementFunction.apply(2);
        System.out.println("Number two: " + increment2);

        int multiply = multiplyFunction.apply(increment2);
        System.out.println(multiply);

        Function<Integer, Integer> multiplyBy1AndThenMultiply10 =
                incrementFunction.andThen(multiplyFunction);
        System.out.println(multiplyBy1AndThenMultiply10.apply(8));

        // BiFunction
        int result = incrementAndMultiply(4, 100);
        System.out.println(result);

        int resultBi = biFunction.apply(4, 100);
        System.out.println(resultBi);
    }

    // Function có 2 tham số, tham số đầu tiên là kiểu dữ liệu đầu vào, tham số thứ 2 là kiểu dữ liệu đầu ra
    static Function<Integer, Integer> incrementFunction = number -> number + 1;

    static Function<Integer, Integer> multiplyFunction = number -> number * 10;

    static int increment(int number) {
        return number + 1;
    }

    static BiFunction<Integer, Integer, Integer> biFunction =
            (a, b) -> (a + 1) * b;

    static int incrementAndMultiply(int number, int multiply) {
        return (number + 1) * multiply;
    }
}
