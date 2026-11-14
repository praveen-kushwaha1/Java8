package com.java.p02_functional_interfaces;

import java.util.function.BiFunction;

public class BiFunctionDemo {

    public static void main(String[] args) {

        // First input: Monthly salary
        // Second input: Annual bonus
        // Return: Annual salary
        BiFunction<Integer, Integer, Integer> calculateSalary =
                (monthlySalary, bonus) ->
                        (monthlySalary * 12) + bonus;

        int annualSalary =
                calculateSalary.apply(50000, 100000);

        System.out.println(annualSalary);

        // Concatenate two strings
        BiFunction<String, String, String> fullName =
                (firstName, lastName) ->
                        firstName + " " + lastName;

        System.out.println(
                fullName.apply("Praveen", "Kushwaha")
        );
    }
}

//2.6 BiFunction – Transform Two Inputs into One Output
//Definition: BiFunction accepts two inputs and returns one result.
//
//Syntax:
//BiFunction<T, U, R>
//
//
//Abstract method:
//R apply(T t, U u);