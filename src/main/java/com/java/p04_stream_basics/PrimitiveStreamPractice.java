package com.java.p04_stream_basics;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.DoubleStream;

/**
 * Java 8+ Primitive Streams - Interview Practice & Revision Notes
 * Covers: IntStream, LongStream, DoubleStream, Creation, Statistics, and Boxing.
 */
public class PrimitiveStreamPractice {

    public static void main(String[] args) {

        // =========================================================================
        // 1. CREATION METHODS (IntStream, LongStream, DoubleStream)
        // =========================================================================

        // A. Using IntStream.of()
        IntStream intStream1 = IntStream.of(1, 2, 3, 4, 5);

        // B. Using range() (exclusive of end) vs rangeClosed() (inclusive of end)
        System.out.print("Range (1 to 5 exclusive): ");
        IntStream.range(1, 5).forEach(i -> System.out.print(i + " ")); // 1 2 3 4
        System.out.println();

        System.out.print("RangeClosed (1 to 5 inclusive): ");
        IntStream.rangeClosed(1, 5).forEach(i -> System.out.print(i + " ")); // 1 2 3 4 5
        System.out.println();

        // C. From arrays
        int[] numbersArray = {10, 20, 30, 40, 50};
        IntStream arrayStream = Arrays.stream(numbersArray);

        // D. Infinite streams with limit() (iterate or generate)
        System.out.print("Even numbers (iterate): ");
        IntStream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(i -> System.out.print(i + " ")); // 0 2 4 6 8
        System.out.println();


        // =========================================================================
        // 2. INTERMEDIATE OPERATIONS & BOXING
        // =========================================================================

        // Q1. Filter and square primitive values
        int[] squaredEvens = IntStream.rangeClosed(1, 6)
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .toArray(); // Directly collecting into a primitive array!
        System.out.println("Squared Evens Array: " + Arrays.toString(squaredEvens));


        // Q2. Boxing: Converting Primitive Stream to Object Stream (IntStream -> Stream<Integer>)
        // Note: Use .boxed() when you need to collect into a generic Collection like List<Integer>
        List<Integer> boxedList = IntStream.of(1, 2, 3, 4)
                .boxed()
                .collect(Collectors.toList());
        System.out.println("Boxed List<Integer>: " + boxedList);


        // =========================================================================
        // 3. TERMINAL OPERATIONS & SUMMARY STATISTICS (The Interview Favorite)
        // =========================================================================

        int[] scores = {85, 90, 78, 92, 88};

        // Standard terminal calculations
        int sum = IntStream.of(scores).sum();
        OptionalDouble average = IntStream.of(scores).average();
        int max = IntStream.of(scores).max().orElse(0);
        int min = IntStream.of(scores).min().orElse(0);
        long count = IntStream.of(scores).count();

        System.out.println("Sum: " + sum + ", Max: " + max + ", Min: " + min + ", Count: " + count);
        System.out.println("Average: " + (average.isPresent() ? average.getAsDouble() : 0.0));


        // Interview Pro-Tip: summaryStatistics()
        // Computes count, sum, min, average, and max in a SINGLE pass through the stream!
        IntSummaryStatistics stats = IntStream.of(scores).summaryStatistics();
        System.out.println("--- IntSummaryStatistics ---");
        System.out.println("Summary Stats: " + stats);
        System.out.println("Get Max from Stats: " + stats.getMax());
        System.out.println("Get Average from Stats: " + stats.getAverage());


        // =========================================================================
        // 4. LONGSTREAM AND DOUBLESTREAM EXAMPLES
        // =========================================================================

        // A. LongStream (ideal for timestamps, large identifiers, or heavy arithmetic)
        long totalMemoryBytes = LongStream.of(1024L, 2048L, 4096L)
                .sum();
        System.out.println("Total Memory (LongStream): " + totalMemoryBytes + " bytes");

        // LongStream range example
        long factorial5 = LongStream.rangeClosed(1, 5)
                .reduce(1, (a, b) -> a * b);
        System.out.println("Factorial of 5 (LongStream reduce): " + factorial5);


        // B. DoubleStream (ideal for financial calculations, coordinates, measurements)
        double[] prices = {19.99, 5.49, 120.00, 45.50};

        DoubleSummaryStatistics priceStats = DoubleStream.of(prices)
                .summaryStatistics();

        System.out.println("--- DoubleStream Summary ---");
        System.out.println("Total Price Sum: " + priceStats.getSum());
        System.out.println("Highest Price: " + priceStats.getMax());


        // =========================================================================
        // 5. CONVERSION BETWEEN PRIMITIVE STREAMS
        // =========================================================================

        // Converting IntStream to DoubleStream or LongStream
        DoubleStream doubleStream = IntStream.of(1, 2, 3)
                .asDoubleStream(); // or mapToDouble(i -> (double) i)

        double[] convertedValues = doubleStream.map(d -> d * 1.5).toArray();
        System.out.println("Converted & Scaled Doubles: " + Arrays.toString(convertedValues));
    }
}