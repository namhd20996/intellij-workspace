import java.util.Optional;

public class IfPresent {

    public static void main(String[] args) {
        Person person = new Person("duynam", null);
        person.getEmail().ifPresent(System.out::printf);
//        System.out.println(person.getEmail().get());
        System.out.println(person.getEmail().map(String::toUpperCase).orElse("email not value"));
        //Sử dụng isPresent
        if(person.getEmail().isPresent()){
            String email = person.getEmail().get();
            System.out.println(email.toUpperCase());
        }else{
            System.out.println("email not provided");
        }
    }

    static class Person{
        private final String name;
        private final String email;

        public Person(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public Optional<String> getEmail() {
            return Optional.ofNullable(email);
        }
    }
}
