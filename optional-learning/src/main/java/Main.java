import java.util.Optional;

public class Main {

    public static void main(String[] args) {
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.isPresent());
        System.out.println(!empty.isPresent());

        Optional<String> hello = Optional.ofNullable("Hello");
        String orElse = hello
                            .map(str -> str.toUpperCase())
                            .orElseGet(()->{
                                return "word";
                            });
        System.out.println(orElse);

        Optional<String> helloV1 = Optional.ofNullable(null);
//        helloV1.ifPresent(value -> System.out.println(value));
        // ifPresent nhận vào Consumer: void có nhận vào value nhưng không trả về value
        // khi dữ liệu đầu vào có sẽ xuất thông tin ra và ngược thì nó sẽ không hiện vlaue
        helloV1.ifPresent(System.out::println);

        Optional<String> helloV2 = Optional.ofNullable("Hello");
        helloV2.ifPresent(System.out::println);

        Optional<String> helloV3 = Optional.ofNullable(null);


    }
}
