package com.java.p02_functional_interfaces;

import java.util.function.BiConsumer;

public class BiConsumerDemo {

    public static void main(String[] args) {

        BiConsumer<String, Integer> employeeDetails =
                (name, salary) -> {
                    System.out.println("Name: " + name);
                    System.out.println("Salary: " + salary);
                };

        employeeDetails.accept("Praveen", 50000);

        // Print key-value pair
        BiConsumer<String, String> printKeyValue =
                (key, value) ->
                        System.out.println(key + " = " + value);

        printKeyValue.accept("City", "Bengaluru");
    }
}

//2.7 BiConsumer – Consume Two Inputs Without Returning Anything
//Definition: BiConsumer accepts two inputs and returns nothing.
//
//        Syntax:
//BiConsumer<T, U>
//
//
//Abstract method:
//void accept(T t, U u);
//
//
//Example