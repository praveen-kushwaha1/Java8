package com.java.p04_method_references;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Consumer;

/**
 * Demonstrates static method references.
 *
 * Static method references point to a static method
 * using ClassName::methodName syntax.
 */
public class StaticMethodReference {

    // Static method to calculate square of a number
    public static int square(int number) {
        return number * number;
    }

    public static void print(String message) {
        System.out.println(message);
    }

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, -2, 3, -4, 5);
        // Lambda expression calling Math.abs()
         numbers.stream().forEach(i-> System.out.println(i));

        // Method reference calling Math.abs()
        numbers.stream().forEach(System.out::println);



        // Static method reference to our own method
        Function<Integer, Integer> squareFunction =
                StaticMethodReference::square;

        System.out.println(squareFunction.apply(5));

        // Static method reference with Consumer
        Consumer<String> printMessage =
                StaticMethodReference::print;

        printMessage.accept("Hello Java 8");

        List<Integer> amounts = List.of(100, 500, 200, 900);

        int maxAmount = amounts.stream()
                .max(Integer::compare)
                .orElse(0);

        System.out.println(maxAmount);

    }

}
