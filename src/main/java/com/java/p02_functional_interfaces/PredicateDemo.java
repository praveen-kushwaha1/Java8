package com.java.p02_functional_interfaces;

import java.util.List;
import java.util.function.Predicate;

public class PredicateDemo {

    public static void main(String[] args) {


//        Definition: Predicate takes one input and returns a boolean value (true or false).
//
//        It is mainly used for filtering and condition checking.
//
//        Syntax:
//        Predicate<T>
//
//
//        Abstract method:
//        boolean test(T t);

        // Check whether employee salary is greater than 50000
        Predicate<Integer> highSalary =
                salary -> salary > 50000;

        // test() executes the condition
        System.out.println(highSalary.test(60000));
        System.out.println(highSalary.test(30000));

        Predicate<String> isJava =
                language -> language.equals("Java");

        System.out.println(isJava.test("Java"));   // true
        System.out.println(isJava.test("Python")); // false

        Predicate<Integer> greaterThan10 =
                number -> number > 10;

        Predicate<Integer> lessThan100 =
                number -> number < 100;

// Both conditions must be true
        Predicate<Integer> between10And100 =
                greaterThan10.and(lessThan100);

        System.out.println(between10And100.test(50));  // true
        System.out.println(between10And100.test(150)); // false

// At least one condition must be true
        Predicate<Integer> condition =
                greaterThan10.or(lessThan100);

        System.out.println(condition.test(5)); // true

// Reverse the condition
        System.out.println(greaterThan10.negate().test(5)); // true

//Interview point: Predicate is commonly used with Stream.filter()
        List<Integer> numbers = List.of(10, 15, 20, 25, 30);

        numbers.stream()
                .filter(number -> number > 20)
                .forEach(System.out::println);
    }
}