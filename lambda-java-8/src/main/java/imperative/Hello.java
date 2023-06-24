package imperative;

@FunctionalInterface
// Khi báo Functional Interface là một interface (chắc chắn rồi) chỉ chứa một và chỉ một abtract method
public interface Hello {

//    String sayHello();
//     String sayHello(String name);

    String sayHello(String name, String com);

    class LambdaExpression {
        public static void main(String[] args) {
            // Không có tham số
//            Hello s = () -> {
//                return "Hello Lambda.";
//            };
//            System.out.println(s.sayHello());

            // Có 1 tham số
//            Hello s = name -> "Hello, " + name;
//            System.out.println(s.sayHello("Lambda"));

            // Có 2 tham số
            Hello s = (name, com) -> "Hello " + name + " Welcome to " + com;
            System.out.println(s.sayHello("Nam", "Lambda"));
        }
    }
}
