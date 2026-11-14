package com.java.p02_functional_interfaces;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class UnaryOperatorDemo {

    public static void main(String[] args) {

//        .8 UnaryOperator – Takes One Input and Returns the Same Type
//        Definition: UnaryOperator is a special type of Function where input and output types are the same.
//
//                It extends Function<T, T>.
//
//        Syntax:
//        UnaryOperator<T>
//
//
//        Abstract method:
//        T apply(T t);
        UnaryOperator<Integer> increase =
                number -> number + 10;

        System.out.println(increase.apply(10)); // 20

        // Convert String into uppercase
        UnaryOperator<String> uppercase =
                text -> text.toUpperCase();

        System.out.println(
                uppercase.apply("java")
        );

        // Square a number
        UnaryOperator<Integer> square =
                number -> number * number;

        System.out.println(square.apply(5));

        // Input String, Output Integer
        Function<String, Integer> getLength =
                text -> text.length();

// Input Integer, Output Integer
        UnaryOperator<Integer> square1 =
                number -> number * number;
    }
}