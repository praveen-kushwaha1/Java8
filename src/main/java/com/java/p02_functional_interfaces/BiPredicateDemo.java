package com.java.p02_functional_interfaces;

import java.util.function.BiPredicate;

public class BiPredicateDemo {

    public static void main(String[] args) {

//        2.5 BiPredicate – Check a Condition Using Two Inputs
//        Definition: BiPredicate accepts two inputs and returns a boolean.
//
//                Syntax:
//        BiPredicate<T, U>
//
//
//        Abstract method:
//        boolean test(T t, U u);

        BiPredicate<Integer, Integer> isEqual =
                (number1, number2) -> number1.equals(number2);

        System.out.println(isEqual.test(10, 10)); // true
        System.out.println(isEqual.test(10, 20)); // false

        // Check username and password
        BiPredicate<String, String> loginCheck =
                (username, password) ->
                        username.equals("admin")
                        && password.equals("1234");

        System.out.println(
                loginCheck.test("admin", "1234")
        );
    }
}

//Real-world use cases:
//        - Username and password validation.
//        - Comparing two objects.
//- Checking whether an employee belongs to a department.
//        - Validating two input values together.