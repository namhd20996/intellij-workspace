import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        Optional<Object> test = Optional.empty();

        // Kiểm tra rỗng
        System.out.println(test.isPresent());
        
    }
}
