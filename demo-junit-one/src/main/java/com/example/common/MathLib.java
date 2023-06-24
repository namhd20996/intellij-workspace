package com.example.common;

public class MathLib {

    public int divide(int a, int b) {
        if (b == 0)
            throw new ArithmeticException("Divide by zero");
        return a / b;
    }

    public int plus(int a, int b) {
        return a + b;
    }

    public int subtraction(int a, int b) {
        return a - b;
    }
}
