package com.java.p01_basics;

import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.BiConsumer;
import java.util.function.UnaryOperator;
import java.util.function.BinaryOperator;

/**
 * ==========================================================
 * JAVA 8 LAMBDA EXPRESSION - COMPLETE REVISION CLASS
 * ==========================================================
 *
 * Topics covered:
 * 1. What is Lambda Expression?
 * 2. Lambda syntax
 * 3. Functional Interface
 * 4. Lambda with zero, one, and multiple parameters
 * 5. Lambda with return value
 * 6. Predicate
 * 7. Function
 * 8. Consumer
 * 9. Supplier
 * 10. BiPredicate, BiFunction, BiConsumer
 * 11. UnaryOperator and BinaryOperator
 * 12. Lambda with Runnable
 * 13. Effectively final variable
 * 14. Lambda vs Anonymous Inner Class
 * 15. Lambda limitations
 *
 * Java Version: Java 8
 */
public class LambdaExpressionRevision {

    // ======================================================
    // 1. FUNCTIONAL INTERFACE
    // ======================================================

    /**
     * A Functional Interface contains exactly one abstract method.
     *
     * Lambda expression provides implementation for this method.
     *
     * @FunctionalInterface helps compiler validate the interface.
     */
    @FunctionalInterface
    interface Greeting {

        // Single abstract method
        void sayHello();
    }

    // ======================================================
    // 2. FUNCTIONAL INTERFACE WITH PARAMETERS
    // ======================================================

    @FunctionalInterface
    interface Calculator {

        // Takes two parameters and returns an integer
        int calculate(int a, int b);
    }

    // ======================================================
    // 3. FUNCTIONAL INTERFACE WITH RETURN VALUE
    // ======================================================

    @FunctionalInterface
    interface Square {

        int findSquare(int number);
    }

    // ======================================================
    // MAIN METHOD
    // ======================================================

    public static void main(String[] args) {

        // ==================================================
        // EXAMPLE 1: LAMBDA WITHOUT PARAMETERS
        // ==================================================

        /*
         * Syntax:
         * () -> { logic }
         *
         * Greeting has one abstract method sayHello().
         * Lambda provides the implementation of sayHello().
         */

        Greeting greeting = () -> {
            System.out.println("Hello Java 8");
        };

        greeting.sayHello();


        // ==================================================
        // EXAMPLE 2: LAMBDA WITH PARAMETERS
        // ==================================================

        /*
         * Syntax:
         * (parameter1, parameter2) -> logic
         *
         * Lambda implementation of Calculator.
         */

        Calculator addition = (a, b) -> {
            return a + b;
        };

        System.out.println("Addition: " + addition.calculate(10, 20));


        // ==================================================
        // EXAMPLE 3: LAMBDA WITH SINGLE EXPRESSION
        // ==================================================

        /*
         * If lambda body contains only one expression,
         * curly braces and return keyword can be omitted.
         */

        Calculator multiplication = (a, b) -> a * b;

        System.out.println("Multiplication: "
                + multiplication.calculate(5, 4));


        // ==================================================
        // EXAMPLE 4: LAMBDA WITH RETURN VALUE
        // ==================================================

        Square square = number -> number * number;

        System.out.println("Square: " + square.findSquare(5));


        // ==================================================
        // EXAMPLE 5: PREDICATE FUNCTIONAL INTERFACE
        // ==================================================

        /*
         * Predicate<T>
         *
         * Takes one input and returns boolean.
         *
         * Method: test()
         *
         * Use case: filtering or checking conditions.
         */

        Predicate<Integer> isEven = number -> number % 2 == 0;

        System.out.println("Is 10 even? " + isEven.test(10));
        System.out.println("Is 7 even? " + isEven.test(7));


        // Predicate with String

        Predicate<String> isJava = name -> name.equals("Java");

        System.out.println("Is Java? " + isJava.test("Java"));


        // ==================================================
        // EXAMPLE 6: FUNCTION FUNCTIONAL INTERFACE
        // ==================================================

        /*
         * Function<T, R>
         *
         * T = Input type
         * R = Return type
         *
         * Method: apply()
         *
         * Use case: converting one value into another.
         */

        Function<Integer, Integer> doubleNumber = number -> number * 2;

        System.out.println("Double: " + doubleNumber.apply(10));


        // Convert String into Integer

        Function<String, Integer> stringLength = text -> text.length();

        System.out.println("Length: " + stringLength.apply("Java"));


        // ==================================================
        // EXAMPLE 7: CONSUMER FUNCTIONAL INTERFACE
        // ==================================================

        /*
         * Consumer<T>
         *
         * Takes input but returns nothing.
         *
         * Method: accept()
         *
         * Use case: printing, logging, processing.
         */

        Consumer<String> printName =
                name -> System.out.println("Name: " + name);

        printName.accept("Praveen");


        // ==================================================
        // EXAMPLE 8: SUPPLIER FUNCTIONAL INTERFACE
        // ==================================================

        /*
         * Supplier<T>
         *
         * Takes no input but returns a value.
         *
         * Method: get()
         *
         * Use case: generating or supplying values.
         */

        Supplier<String> message = () -> "Welcome to Java 8";

        System.out.println(message.get());


        // ==================================================
        // EXAMPLE 9: BIPREDICATE
        // ==================================================

        /*
         * BiPredicate<T, U>
         *
         * Takes two inputs and returns boolean.
         *
         * Method: test()
         */

        BiPredicate<Integer, Integer> isGreater =
                (a, b) -> a > b;

        System.out.println("Is 20 greater than 10? "
                + isGreater.test(20, 10));


        // ==================================================
        // EXAMPLE 10: BIFUNCTION
        // ==================================================

        /*
         * BiFunction<T, U, R>
         *
         * Takes two inputs and returns one output.
         *
         * Method: apply()
         */

        BiFunction<Integer, Integer, Integer> sum =
                (a, b) -> a + b;

        System.out.println("BiFunction Sum: " + sum.apply(10, 20));


        // ==================================================
        // EXAMPLE 11: BICONSUMER
        // ==================================================

        /*
         * BiConsumer<T, U>
         *
         * Takes two inputs and returns nothing.
         *
         * Method: accept()
         */

        BiConsumer<String, Integer> employeeDetails =
                (name, age) -> System.out.println(
                        "Employee Name: " + name + ", Age: " + age
                );

        employeeDetails.accept("Praveen", 26);


        // ==================================================
        // EXAMPLE 12: UNARY OPERATOR
        // ==================================================

        /*
         * UnaryOperator<T>
         *
         * Takes one input and returns the same type.
         *
         * It extends Function<T, T>.
         */

        UnaryOperator<Integer> squareNumber =
                number -> number * number;

        System.out.println("UnaryOperator Square: "
                + squareNumber.apply(6));


        // ==================================================
        // EXAMPLE 13: BINARY OPERATOR
        // ==================================================

        /*
         * BinaryOperator<T>
         *
         * Takes two inputs of same type.
         * Returns output of the same type.
         *
         * It extends BiFunction<T, T, T>.
         */

        BinaryOperator<Integer> addNumbers =
                (a, b) -> a + b;

        System.out.println("BinaryOperator Sum: "
                + addNumbers.apply(10, 20));


        // ==================================================
        // EXAMPLE 14: LAMBDA WITH RUNNABLE
        // ==================================================

        /*
         * Runnable is a Functional Interface.
         *
         * Abstract method: run()
         *
         * Lambda can provide the implementation.
         */

        Runnable task = () -> {
            System.out.println("Runnable executed using Lambda");
        };

        task.run();


        // ==================================================
        // EXAMPLE 15: EFFECTIVELY FINAL VARIABLE
        // ==================================================

        /*
         * Lambda can access local variables if they are
         * final or effectively final.
         *
         * Effectively final means:
         * Variable is assigned once and never reassigned.
         */

        int bonus = 5000;

        Consumer<String> employeeBonus = name ->
                System.out.println(name + " bonus: " + bonus);

        employeeBonus.accept("Praveen");

        // bonus = 10000;
        // Uncommenting the above reassignment causes a compile error
        // because bonus is captured by the lambda.


        // ==================================================
        // EXAMPLE 16: ANONYMOUS CLASS VS LAMBDA
        // ==================================================

        /*
         * Traditional way before Java 8:
         */

        Runnable traditionalTask = new Runnable() {
            @Override
            public void run() {
                System.out.println("Traditional Anonymous Class");
            }
        };

        traditionalTask.run();


        /*
         * Java 8 Lambda way:
         */

        Runnable lambdaTask = () ->
                System.out.println("Lambda Expression");

        lambdaTask.run();


        // ==================================================
        // EXAMPLE 17: PREDICATE COMBINATIONS
        // ==================================================

        /*
         * Predicate supports:
         * and()
         * or()
         * negate()
         */

        Predicate<Integer> greaterThanTen =
                number -> number > 10;

        Predicate<Integer> lessThanHundred =
                number -> number < 100;

        Predicate<Integer> combined =
                greaterThanTen.and(lessThanHundred);

        System.out.println("Is 50 between 10 and 100? "
                + combined.test(50));

        System.out.println("Negate result: "
                + greaterThanTen.negate().test(5));


        // ==================================================
        // END OF JAVA 8 LAMBDA EXAMPLES
        // ==================================================

        System.out.println("Lambda Revision Completed!");

    }
}
