package toantulambda;

public class App {
    /**
     * Xử lý giá trị của tham số a và b.
     * Lúc này chúng ta chưa biết hàm accept(a,b) trả về giá trị gì,
     * vì nó chỉ là một hàm trừu tượng chưa có thân hàm.
     * Chúng ta sẽ cung cấp thân hàm cho phương thức MyFunction.accept()
     * trong hàm main().
     */
    public int action(int a, int b, MyFunction function) {
        return function.accept(a, b);
    }

    public static void main(String[] args) {
        App app = new App();
        // tạo đối tượng nặc danh addFunc và cung cấp thân hàm cho hàm accept()
        // bây giờ hàm accept() sẽ có thân hàm giống như hàm MyUtils.add().
        // nghĩa là hệ thống copy hàm MyUtils.add() như một bản cài đặt
        // cho hàm accept()
        MyFunction addFunc = MyUtils::add;
        int c1 = app.action(10, 2, addFunc);
        System.out.println(c1);

        MyFunction minusFunc = MyUtils::minus;
        int c2 = app.action(10, 2, minusFunc);
        System.out.println("Hiệu 2 số: " + c2);

        MyFunction maxFunc = Math::max;
        int c3 = app.action(10, 2, maxFunc);
        System.out.println("MAX: " + c3);

        MyFunction minFunc = Math::min;
        int c4 = app.action(10, 2, minFunc);
        System.out.println("MIN: " + c4);
    }
}
