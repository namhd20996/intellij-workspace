package functioninterface;

import java.util.function.Predicate;

public class _Predicate {

    public static void main(String[] args) {
        System.out.println(predicate.test("0968997434"));

        // and ở đây giống như toán tử && phải cả 2 trả về true -> true
        System.out.println("Is phone number valid and contains number 3: " +
            predicate.and(isContainsNumber3).test("0868997434")
        );

    }

    static Predicate<String> predicate =
            str -> str.startsWith("09") && str.length()==10;

    static Predicate<String> isContainsNumber3 =
            str -> str.contains("3");
}
