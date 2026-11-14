package com.java.p02_functional_interfaces;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

public class BinaryOperatorDemo {

    public static void main(String[] args) {

//        2.9 BinaryOperator – Takes Two Inputs of the Same Type and Returns the Same Type
//        Definition: BinaryOperator is a special type of BiFunction where both input types and the return type are the same.
//
//                It extends BiFunction<T, T, T>.
//
//        Syntax:
//        BinaryOperator<T>
//
//
//        Abstract method:
//        T apply(T t1, T t2);
//
//
//        Example

        BinaryOperator<Integer> addition =
                (number1, number2) -> number1 + number2;

        System.out.println(addition.apply(10, 20));

        // Find maximum number
        BinaryOperator<Integer> maximum =
                (number1, number2) ->
                        number1 > number2 ? number1 : number2;

        System.out.println(maximum.apply(50, 30));

        // Combine two strings
        BinaryOperator<String> concatenate =
                (first, second) -> first + " " + second;

        System.out.println(
                concatenate.apply("Hello", "Java")
        );

        // Two Integer inputs, String output
        BiFunction<Integer, Integer, String> result =
                (a, b) -> "Sum: " + (a + b);

// Two Integer inputs, Integer output
        BinaryOperator<Integer> sum =
                (a, b) -> a + b;
    }
}