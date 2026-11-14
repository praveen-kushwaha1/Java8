package com.java.p02_functional_interfaces;

import java.util.List;
import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args) {


//        2.2 Function – Transform One Value into Another
//        Definition: Function takes one input and returns another value. The input and output types can be different.
//
//        Syntax:
//        Function<T, R>
//
//
//        Abstract method:
//        R apply(T t);
        // Input: String
        // Output: String
        Function<String, String> convertToUppercase =
                name -> name.toUpperCase();

        System.out.println(
                convertToUppercase.apply("praveen")
        );

        // Input: String
        // Output: Integer
        Function<String, Integer> getNameLength =
                name -> name.length();

        System.out.println(
                getNameLength.apply("Praveen")
        );

        // Input: Integer
        // Output: String
        Function<Integer, String> convertToString =
                number -> "Employee ID: " + number;

        System.out.println(
                convertToString.apply(101)
        );

        Function<Integer, Integer> multiplyBy2 =
                number -> number * 2;

        Function<Integer, Integer> add10 =
                number -> number + 10;

// First multiply by 2, then add 10
        Function<Integer, Integer> result1 =
                multiplyBy2.andThen(add10);

        System.out.println(result1.apply(5)); // 20

// First add 10, then multiply by 2
        Function<Integer, Integer> result2 =
                multiplyBy2.compose(add10);

        System.out.println(result2.apply(5)); // 30
       /// Interview point: Function is commonly used with Stream.map().
        List<String> names = List.of("java", "spring", "hibernate");

        names.stream()
                .map(name -> name.toUpperCase())
                .forEach(System.out::println);
    }
}