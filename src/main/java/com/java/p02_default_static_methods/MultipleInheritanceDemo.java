package com.java.p02_default_static_methods;

interface A {

    default void show() {
        System.out.println("Show from A");
    }
}

interface B {

    default void show() {
        System.out.println("Show from B");
    }
}

class MyClass implements A, B {

    // Mandatory: resolve the conflict.
    @Override
    public void show() {

        A.super.show();
        B.super.show();

        System.out.println("Show from MyClass");
    }
}

public class MultipleInheritanceDemo {

    public static void main(String[] args) {

        MyClass obj = new MyClass();

        obj.show();
    }
}
//Why do we override show()?
//
//Because both interfaces provide an implementation of the same method. Java cannot automatically decide which implementation to use.
//
//We resolve the conflict using:
//        - A.super.show() → Calls interface A's default implementation.
//        - B.super.show() → Calls interface B's default implementation.
//This is one of the most important Java 8 interview questions.