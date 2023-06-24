package combinatorpattern;

import java.time.LocalDate;

import static combinatorpattern.CustomerRegistrationValidator.*;
import static combinatorpattern.CustomerRegistrationValidator.ValidationResult.SUCCESS;
import static combinatorpattern.CustomerRegistrationValidator.isAnAdult;

public class Main {

    public static void main(String[] args) {
        Customer customer = new Customer(
                "Duy Nam",
                "nam@gmail.com",
                "0968997434",
                LocalDate.of(2000, 1, 1)
        );

        System.out.println(new CustomerValidatorService().isValid(customer));

        // Sử dụng combinator - pattern giúp tùy biến khi có sự thay đổi về valid chỉ cần
        // thêm function valid vào là được còn viết theo cách cũ sẽ phải sửa code ở function isValid.
        // Và ưu điểm khi sử dụng Function interface nếu không gọi apply() thì func sẽ không run khi chạy.
        CustomerRegistrationValidator resultV1 = isEmailValid()
                .and(isPhoneNumberValid())
                .and(isAnAdult());

        ValidationResult result = isEmailValid()
                .and(isPhoneNumberValid())
                .and(isAnAdult())
                .apply(customer);


        System.out.println(result);

        if(result != SUCCESS){
            throw new IllegalStateException(result.name());
        }
    }
}
