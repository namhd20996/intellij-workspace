package combinatorpattern;

import java.time.LocalDate;
import java.time.Period;

public class CustomerValidatorService {

    private Boolean isValidEmail(String email) {
        return email.contains("@");
    }

    private Boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.startsWith("+0");
    }

    private Boolean isAdult(LocalDate dob) {
        return Period.between(dob, LocalDate.now()).getYears() > 16;
    }

    public Boolean isValid(Customer customer) {
        return isValidEmail(customer.getEmail()) &&
                isValidPhoneNumber(customer.getPhoneNumber()) &&
                isAdult(customer.getDob());
    }
}
