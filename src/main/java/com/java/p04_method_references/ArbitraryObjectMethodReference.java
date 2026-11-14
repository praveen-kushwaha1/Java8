package com.java.p04_method_references;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.List;

/**
 * Demonstrates arbitrary object method references.
 *
 * The object on which the instance method executes
 * is provided as the first argument.
 */
public class ArbitraryObjectMethodReference {

    public static void main(String[] args) {

        // Lambda expression
        Function<String, String> lambda =
                str -> str.toUpperCase();

        // Arbitrary object method reference
        Function<String, String> methodReference =
                String::toUpperCase;

        System.out.println(lambda.apply("java"));
        System.out.println(methodReference.apply("spring boot"));

        // String::length
        Function<String, Integer> lengthFunction =
                String::length;

        System.out.println(lengthFunction.apply("Microservices"));

        // String::isEmpty
        Predicate<String> emptyPredicate =
                String::isEmpty;

        System.out.println(emptyPredicate.test(""));
        System.out.println(emptyPredicate.test("Java"));

        // String::compareTo
        List<String> names = List.of("Amit", "Rahul", "Praveen");

        names.stream()
                .sorted(String::compareTo)
                .forEach(System.out::println);
    }
}
