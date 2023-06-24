package functionalinterface;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class _Consumer {

    public static void main(String[] args) {
        // Normal java
        Customer maria = new Customer("Duy Nam", "9999");
        greetCustomer(maria);

        greetCustomerV2(maria, true);

        // Cunsumer Function interface
        // Giống như void không trả về value
        greetCustomerConsumer.accept(maria);

        // BiConsumer
        greetCustomerBiConsumer.accept(maria, false);
    }

    static BiConsumer<Customer, Boolean> greetCustomerBiConsumer =
            (customer, showPhone) ->
                    System.out.println("Hello " + customer.customerName
                            + ", thank for registering phone numer "
                            + (showPhone ? customer.phoneNumber : "****"));

    static void greetCustomerV2(Customer customer, Boolean showPhone) {
        System.out.println("Hello " + customer.customerName
                + ", thank for registering phone numer "
                + (showPhone ? customer.phoneNumber : "****"));
    }

    // Consumer<Customer> greetCustomerConsumer = customer // Customer là kiểu dữ liệu
    // sau dấu = là tên biến
    static Consumer<Customer> greetCustomerConsumer = customer ->
            System.out.println("Hello " + customer.customerName +
                    ", thank for registering phone number "
                    + customer.phoneNumber);

    static void greetCustomer(Customer customer) {
        System.out.println("Hello " + customer.customerName +
                ", thank for registering phone number "
                + customer.phoneNumber);
    }

    static class Customer {
        private final String customerName;
        private final String phoneNumber;

        Customer(String customerName, String phoneNumber) {
            this.customerName = customerName;
            this.phoneNumber = phoneNumber;
        }
    }
}
