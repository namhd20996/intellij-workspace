package conbinatorpattern;

import java.util.function.Function;

import static conbinatorpattern.CustomerRegistrationValidation.*;

public interface CustomerRegistrationValidation extends Function<Customer, ValidationResult> {

    enum ValidationResult {

    }
}
