package com.java.p04_stream_basics;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * A comprehensive demonstration of Java 8 Intermediate Stream Operations:
 * filter, map, flatMap, sorted, distinct, limit, skip, and peek.
 */
public class AllStreamlined {

    public static void main(String[] args) {

        System.out.println("=== 1. FILTER OPERATIONS ===");
        List<Integer> numbers = List.of(10, 15, 20, 25, 30);

        // Filter even numbers (Predicate returns boolean)
        System.out.println("Even numbers:");
        numbers.stream()
                .filter(number -> number % 2 == 0)
                .forEach(System.out::println);

        // Filter numbers greater than 20
        System.out.println("Numbers > 20:");
        numbers.stream()
                .filter(number -> number > 20)
                .forEach(System.out::println);

        // Filter names starting with 'P'
        List<String> names = List.of("Praveen", "Amit", "Pankaj", "Rahul");
        System.out.println("Names starting with 'P':");
        names.stream()
                .filter(name -> name.startsWith("P"))
                .forEach(System.out::println);


        System.out.println("\n=== 2. MAP OPERATIONS (Transformation) ===");
        List<String> namesMap = List.of("praveen", "amit", "rahul");

        // Convert lowercase names to uppercase using method reference
        System.out.println("Uppercase names:");
        namesMap.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        // Convert String to its length
        System.out.println("Name lengths:");
        namesMap.stream()
                .map(String::length)
                .forEach(System.out::println);

        // Convert numbers into their squares (Fixed syntax bug here)
        List<Integer> numbersMap = List.of(1, 2, 3, 4, 5);
        System.out.println("Squares of numbers:");
        numbersMap.stream()
                .map(number -> number * number)
                .forEach(System.out::println);


        System.out.println("\n=== 3. FLATMAP OPERATIONS (Flattening nested structures) ===");
        List<List<Integer>> numbersFlatMap = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(7, 8, 9)
        );

        // map() returns Stream<Stream<Integer>> (nested streams)
        System.out.println("Using map() (results in Stream of Streams):");
        numbersFlatMap.stream()
                .map(list -> list.stream())
                .forEach(System.out::println);

        // flatMap() flattens it down to Stream<Integer>
        System.out.println("Using flatMap() (flattened single stream):");
        numbersFlatMap.stream()
                .flatMap(list -> list.stream())
                .forEach(System.out::println);

        // Splitting sentences into words using flatMap
        List<String> sentences = List.of(
                "Java Spring",
                "Spring Boot",
                "Microservices"
        );
        System.out.println("Flattened words from sentences:");
        sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                .forEach(System.out::println);


        System.out.println("\n=== 4. SORTED OPERATIONS ===");

        // Natural order: ascending
        System.out.println("Numbers sorted ascending:");
        numbers.stream()
                .sorted()
                .forEach(System.out::println);

        // Descending order using Comparator.reverseOrder()
        System.out.println("Numbers sorted descending:");
        numbers.stream()
                .sorted(Comparator.reverseOrder())
                .forEach(System.out::println);

        // Sort Strings alphabetically
        System.out.println("Names sorted alphabetically:");
        names.stream()
                .sorted()
                .forEach(System.out::println);

        // Sort Strings by length
        System.out.println("Names sorted by length:");
        names.stream()
                .sorted(Comparator.comparingInt(String::length))
                .forEach(System.out::println);


        System.out.println("\n=== 5. DISTINCT OPERATIONS (Removing duplicates) ===");
        List<Integer> duplicateNumbers = List.of(10, 20, 10, 30, 20, 40);
        System.out.println("Unique numbers:");
        duplicateNumbers.stream()
                .distinct()
                .forEach(System.out::println);


        System.out.println("\n=== 6. LIMIT & SKIP OPERATIONS ===");

        // Get first 3 elements
        System.out.println("First 3 elements:");
        numbers.stream()
                .limit(3)
                .forEach(System.out::println);

        // Skip first 3 elements
        System.out.println("Skipping first 3 elements:");
        numbers.stream()
                .skip(3)
                .forEach(System.out::println);

        // Skip 2 and then take next 3 (Pagination pattern)
        System.out.println("Skip 2, Limit 3:");
        numbers.stream()
                .skip(2)
                .limit(3)
                .forEach(System.out::println);

        List<Integer> result = numbers.stream()

                // Select even numbers
                .filter(number -> number % 2 == 0)

                // Multiply each number by 2
                .map(number -> number * 2)

                // Remove duplicate values
                .distinct()

                // Sort descending
                .sorted(Comparator.reverseOrder())
                .peek(number -> System.out.println("Peeked value: " + number))

                // Skip first element
                .skip(1)

                // Limit output to 3 elements
                .limit(3)

                // Collect results
                .toList();

        System.out.println(result);
        System.out.println("\n=== 7. PEEK OPERATIONS (Debugging Pipeline) ===");
        // peek() lets you perform an action (like logging) on each element as it passes through
        numbers.stream()
                .peek(number -> System.out.println(" [Debug] Original: " + number))
                .filter(number -> number % 2 == 0)
                .peek(number -> System.out.println(" [Debug] Filtered (Even): " + number))
                .map(number -> number * 2) // Fixed syntax bug here
                .peek(number -> System.out.println(" [Debug] Mapped (*2): " + number))
                .forEach(finalVal -> System.out.println(" --> Final Output: " + finalVal));
    }
}