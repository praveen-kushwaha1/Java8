package com.java.p04_stream_basics;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

public class CollectorsRevision {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(
                10, 20, 10, 30, 20, 40, 50
        );

        List<String> names = List.of(
                "Praveen", "Amit", "Rahul",
                "Amit", "Pankaj", "Rahul"
        );


        // ==================================================
        // 1. toList() - Collect elements into List
        // ==================================================

        System.out.println("\n1. toList():");

        List<Integer> numberList = numbers.stream()
                .collect(Collectors.toList());

        System.out.println(numberList);


        // ==================================================
        // 2. toSet() - Collect unique elements into Set
        // ==================================================

        System.out.println("\n2. toSet():");

        Set<Integer> uniqueNumbers = numbers.stream()
                .collect(Collectors.toSet());

        System.out.println(uniqueNumbers);


        // ==================================================
        // 3. toMap() - Convert elements into Map
        // ==================================================

        System.out.println("\n3. toMap():");

        Map<String, Integer> nameLengthMap = names.stream()
                .distinct()
                .collect(Collectors.toMap(
                        name -> name,
                        String::length
                ));

        System.out.println(nameLengthMap);

        // Handle duplicate keys using merge function
        Map<String, Integer> duplicateMap = names.stream()
                .collect(Collectors.toMap(
                        name -> name,
                        String::length,
                        (oldValue, newValue) -> oldValue
                ));

        System.out.println(duplicateMap);


        // ==================================================
        // 4. groupingBy() - Group elements by condition
        // ==================================================

        System.out.println("\n4. groupingBy():");

        // Group names by length
        Map<Integer, List<String>> namesByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));

        System.out.println(namesByLength);

        // Group numbers into even and odd
        Map<String, List<Integer>> numbersByType = numbers.stream()
                .collect(Collectors.groupingBy(
                        number -> number % 2 == 0 ? "Even" : "Odd"
                ));

        System.out.println(numbersByType);


        // ==================================================
        // 5. partitioningBy() - Divide into true/false groups
        // ==================================================

        System.out.println("\n5. partitioningBy():");

        Map<Boolean, List<Integer>> partitionedNumbers = numbers.stream()
                .collect(Collectors.partitioningBy(
                        number -> number % 2 == 0
                ));

        System.out.println(partitionedNumbers);


        // ==================================================
        // 6. joining() - Join Strings
        // ==================================================

        System.out.println("\n6. joining():");

        // Join without delimiter
        String joinedNames = names.stream()
                .distinct()
                .collect(Collectors.joining());

        System.out.println(joinedNames);

        // Join with delimiter
        String commaSeparatedNames = names.stream()
                .distinct()
                .collect(Collectors.joining(", "));

        System.out.println(commaSeparatedNames);

        // Join with delimiter, prefix and suffix
        String formattedNames = names.stream()
                .distinct()
                .collect(Collectors.joining(", ", "[", "]"));

        System.out.println(formattedNames);


        // ==================================================
        // 7. counting() - Count elements
        // ==================================================

        System.out.println("\n7. counting():");

        long totalCount = numbers.stream()
                .collect(Collectors.counting());

        System.out.println("Total Count: " + totalCount);

        // Count occurrences of each name
        Map<String, Long> nameFrequency = names.stream()
                .collect(Collectors.groupingBy(
                        name -> name,
                        Collectors.counting()
                ));

        System.out.println(nameFrequency);


        // ==================================================
        // 8. summing() and averaging()
        // ==================================================

        System.out.println("\n8. summing() and averaging():");

        // Sum of numbers
        int sum = numbers.stream()
                .collect(Collectors.summingInt(Integer::intValue));

        System.out.println("Sum: " + sum);

        // Average of numbers
        double average = numbers.stream()
                .collect(Collectors.averagingInt(Integer::intValue));

        System.out.println("Average: " + average);

        // Sum of name lengths
        int totalNameLength = names.stream()
                .collect(Collectors.summingInt(String::length));

        System.out.println("Total Name Length: " + totalNameLength);

        // Average name length
        double averageNameLength = names.stream()
                .collect(Collectors.averagingInt(String::length));

        System.out.println("Average Name Length: " + averageNameLength);


        // ==================================================
        // 9. maxBy() and minBy() - Find maximum and minimum
        // ==================================================

        System.out.println("\n9. maxBy() and minBy():");

        // Find maximum number
        Optional<Integer> maximum = numbers.stream()
                .collect(Collectors.maxBy(Comparator.naturalOrder()));

        System.out.println("Maximum: " + maximum.orElse(-1));

        // Find minimum number
        Optional<Integer> minimum = numbers.stream()
                .collect(Collectors.minBy(Comparator.naturalOrder()));

        System.out.println("Minimum: " + minimum.orElse(-1));

        // Find longest name
        Optional<String> longestName = names.stream()
                .collect(Collectors.maxBy(
                        Comparator.comparingInt(String::length)
                ));

        System.out.println("Longest Name: " + longestName.orElse("None"));

        // Find shortest name
        Optional<String> shortestName = names.stream()
                .collect(Collectors.minBy(
                        Comparator.comparingInt(String::length)
                ));

        System.out.println("Shortest Name: " + shortestName.orElse("None"));


        // ==================================================
        // BONUS: Downstream Collectors
        // ==================================================

        System.out.println("\n10. Downstream Collectors:");

        // Count employees/names grouped by length
        Map<Integer, Long> countByLength = names.stream()
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.counting()
                ));

        System.out.println("Count By Length: " + countByLength);

        // Sum numbers grouped by even and odd
        Map<Boolean, Integer> sumByType = numbers.stream()
                .collect(Collectors.partitioningBy(
                        number -> number % 2 == 0,
                        Collectors.summingInt(Integer::intValue)
                ));

        System.out.println("Sum By Even/Odd: " + sumByType);

    }
}
