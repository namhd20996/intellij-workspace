package com.example.lab.model;

@FunctionalInterface
public interface FunctionInterfaceDemo {

    void m1(int x);

    default void m2() {
    }

    public static void m3() {
    }
}
