package com.java.p02_functional_interfaces;

import java.util.function.Supplier;

public class SupplierDemo {

    public static void main(String[] args) {

//        2.4 Supplier – Supply Data Without Taking Input
//        Definition: Supplier does not accept any input but returns a value.
//
//        It is useful for generating values, creating objects, and providing default values.
//
//                Syntax:
//        Supplier<T>
//
//
//        Abstract method:
//        T get();

        // Supplier does not take input
        // It supplies a String
        Supplier<String> welcomeMessage =
                () -> "Welcome to Java 8";

        System.out.println(welcomeMessage.get());

        // Supplier generates a random number
        Supplier<Double> randomNumber =
                () -> Math.random();

        System.out.println(randomNumber.get());

        // Supplier provides a default value
        Supplier<String> defaultName =
                () -> "Guest";

        System.out.println(defaultName.get());

        Supplier<StringBuilder> builderSupplier =
                () -> new StringBuilder("Java");

        StringBuilder builder = builderSupplier.get();

        System.out.println(builder);
    }
}