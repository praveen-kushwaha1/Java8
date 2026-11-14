package com.java.p02_functional_interfaces;

import java.util.function.Consumer;

public class ConsumerDemo {

    public static void main(String[] args) {

//        2.3 Consumer – Consume Data Without Returning Anything
//        Definition: Consumer accepts one input but does not return anything.
//
//                It is mainly used for printing, logging, sending notifications, and processing data.
//
//        Syntax:
//        Consumer<T>
//
//
//        Abstract method:
//        void accept(T t);

        // Consumer accepts a String and prints it
        Consumer<String> printMessage =
                message -> System.out.println(message);

        printMessage.accept("Welcome to Java 8");

        // Consumer for employee details
        Consumer<String> employeeLogger =
                name -> System.out.println(
                        "Employee Name: " + name
                );

        employeeLogger.accept("Praveen");

        Consumer<String> printName =
                name -> System.out.println("Name: " + name);

        Consumer<String> printLength =
                name -> System.out.println("Length: " + name.length());

        Consumer<String> combinedConsumer =
                printName.andThen(printLength);

        combinedConsumer.accept("Praveen");

        //Interview point: Consumer is commonly used with Stream.forEach().
    }
}