package com.java.p04_stream_basics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * A comprehensive demonstration of all standard ways to create Streams in Java 8,
 * complete with explanatory comments for each approach.
 */
public class StreamCreation {

    public static void main(String[] args) {

        // -------------------------------------------------------------
        // 1. STREAM FROM COLLECTIONS (List, Set, etc.)
        // -------------------------------------------------------------
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        Stream<String> namesStream = names.stream();

        System.out.println("=== 1. Collection Streams ===");

        // Sequential stream: processes elements one by one in a single thread
        System.out.println("Sequential Stream (filtering names starting with 'A'):");
        names.stream()
                .filter(name -> name.startsWith("A"))
                .forEach(System.out::println);

        // Parallel stream: splits elements across multiple threads for concurrent processing
        System.out.println("\nParallel Stream:");
        names.parallelStream()
                .forEach(name -> System.out.println(name + " [Thread: " + Thread.currentThread().getName() + "]"));


        // -------------------------------------------------------------
        // 2. STREAM FROM ARRAYS
        // -------------------------------------------------------------
        System.out.println("\n=== 2. Array Streams ===");
        String[] nameArray = {"Alice", "Bob", "Charlie"};
        Stream<String> nameStream = Arrays.stream(nameArray);

        // Using the Arrays.utility class to create a stream from an array
        Arrays.stream(nameArray)
                .forEach(System.out::println);


        // -------------------------------------------------------------
        // 3. STREAM FROM INDIVIDUAL VALUES (Stream.of())
        // -------------------------------------------------------------
        System.out.println("\n=== 3. Stream.of() ===");

        // Directly passes individual elements to create a stream

        Stream<String> individualValuesStream = Stream.of("Alice", "Bob", "Charlie");
        Stream.of("Alice", "Bob", "Charlie")
                .map(String::toUpperCase)
                .forEach(System.out::println);


        // -------------------------------------------------------------
        // 4. PRIMITIVE STREAMS (IntStream, LongStream, DoubleStream)
        // -------------------------------------------------------------
        System.out.println("\n=== 4. Primitive Streams (Ranges) ===");

        // IntStream.range(start, end) -> Upper bound is EXCLUSIVE (stops before 6)
        System.out.print("IntStream.range (1 to 5): ");
        IntStream.range(1, 6)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();

        // IntStream.rangeClosed(start, end) -> Upper bound is INCLUSIVE (includes 5)
        System.out.print("IntStream.rangeClosed (1 to 5): ");
        IntStream.rangeClosed(1, 5)
                .forEach(n -> System.out.print(n + " "));

        System.out.println();


        // -------------------------------------------------------------
        // 5. INFINITE STREAMS USING Stream.iterate()
        // -------------------------------------------------------------
        System.out.println("\n=== 5. Infinite Stream using iterate() == /");

        // Takes an initial seed value and a UnaryOperator function to generate sequence.
        // NOTE: .limit() is mandatory here to prevent an infinite loop.
        System.out.println("First 5 even numbers starting from 0:");
        Stream.iterate(0, n -> n + 2)
                .limit(5)
                .forEach(System.out::println); // Output: 0, 2, 4, 6, 8


        // -------------------------------------------------------------
        // 6. INFINITE STREAMS USING Stream.generate()
        // -------------------------------------------------------------
        System.out.println("\n=== 6. Infinite Stream using generate() ===");

        // Takes a Supplier functional interface to generate values dynamically.
        // NOTE: .limit() is mandatory to restrict the number of emitted items.
        System.out.println("5 random numbers:");
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }
}