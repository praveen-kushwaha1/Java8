package com.java.p04_stream_basics;

import java.util.List;
import java.util.Optional;
import java.util.Comparator;

public class TerminalOperationsRevision {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(10, 20, 30, 40, 50, 20);

        // ==================================================
        // 1. ForEachDemo - forEach()
        // ==================================================

        System.out.println("\n1. forEach():");

        numbers.stream()
                .forEach(System.out::println);


        // ==================================================
        // 2. ReduceDemo - reduce()
        // ==================================================

        System.out.println("\n2. reduce():");

        // Sum of all numbers
        int sum = numbers.stream()
                .reduce(0, Integer::sum);

        System.out.println("Sum: " + sum);

        // Product of all numbers
        int product = numbers.stream()
                .reduce(1, (a, b) -> a * b);

        System.out.println("Product: " + product);

        // Reduce without identity
        Optional<Integer> optionalSum = numbers.stream()
                .reduce(Integer::sum);

        System.out.println("Optional Sum: " + optionalSum.orElse(0));


        // ==================================================
        // 3. CountDemo - count()
        // ==================================================

        System.out.println("\n3. count():");

        long totalCount = numbers.stream()
                .count();

        System.out.println("Total Count: " + totalCount);

        long evenCount = numbers.stream()
                .filter(number -> number % 2 == 0)
                .count();

        System.out.println("Even Count: " + evenCount);


        // ==================================================
        // 4. MinMaxDemo - min() and max()
        // ==================================================

        System.out.println("\n4. min() and max():");

        int minimum = numbers.stream()
                .min(Comparator.naturalOrder())
                .orElseThrow();

        System.out.println("Minimum: " + minimum);

        int maximum = numbers.stream()
                .max(Comparator.naturalOrder())
                .orElseThrow();

        System.out.println("Maximum: " + maximum);


        // ==================================================
        // 5. AnyMatchDemo - anyMatch()
        // ==================================================

        System.out.println("\n5. anyMatch():");

        boolean anyEven = numbers.stream()
                .anyMatch(number -> number % 2 == 0);

        System.out.println("Any Even: " + anyEven);

        boolean anyGreaterThan100 = numbers.stream()
                .anyMatch(number -> number > 100);

        System.out.println("Any Greater Than 100: " + anyGreaterThan100);


        // ==================================================
        // 6. AllMatchDemo - allMatch()
        // ==================================================

        System.out.println("\n6. allMatch():");

        boolean allPositive = numbers.stream()
                .allMatch(number -> number > 0);

        System.out.println("All Positive: " + allPositive);

        boolean allGreaterThan20 = numbers.stream()
                .allMatch(number -> number > 20);

        System.out.println("All Greater Than 20: " + allGreaterThan20);


        // ==================================================
        // 7. NoneMatchDemo - noneMatch()
        // ==================================================

        System.out.println("\n7. noneMatch():");

        boolean noneNegative = numbers.stream()
                .noneMatch(number -> number < 0);

        System.out.println("None Negative: " + noneNegative);

        boolean noneGreaterThan100 = numbers.stream()
                .noneMatch(number -> number > 100);

        System.out.println("None Greater Than 100: " + noneGreaterThan100);

    }
}
