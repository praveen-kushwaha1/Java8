package com.java.p07_optional;

import java.util.Optional;

public class OptionalCompleteDemo {

    public static void main(String[] args) {


        String name = null;
        //System.out.println(name.toUpperCase()); // Throws NullPointerException

        Optional<String> name5 = Optional.ofNullable(null);
        System.out.println(name5.map(String::toUpperCase).orElse("Default Name")); // Default Name

        // =====================================================
        // 1. OPTIONAL CREATION
        // =====================================================

        // of(): Value must not be null
        Optional<String> name1 = Optional.of("Praveen");

        // ofNullable(): Value may be null
        Optional<String> name2 = Optional.ofNullable(null);

        // empty(): Creates an empty Optional
        Optional<String> name3 = Optional.empty();

        System.out.println("1. OPTIONAL CREATION");
        System.out.println(name1);
        System.out.println(name2);
        System.out.println(name3);


        // =====================================================
        // 2. isPresent()
        // =====================================================

        // Checks whether a value is available
        System.out.println("\n2. isPresent()");

        System.out.println(name1.isPresent()); // true
        System.out.println(name2.isPresent()); // false


        // =====================================================
        // 3. ifPresent()
        // =====================================================

        // Executes Lambda only when value is present
        System.out.println("\n3. ifPresent()");

        name1.ifPresent(value ->
                System.out.println("Name: " + value));

        name2.ifPresent(value ->
                System.out.println("Name: " + value));


        // =====================================================
        // 4. orElse()
        // =====================================================

        // Returns actual value or default value
        System.out.println("\n4. orElse()");

        String result1 = name1.orElse("Unknown");
        String result2 = name2.orElse("Unknown");

        System.out.println(result1); // Praveen
        System.out.println(result2); // Unknown


        // =====================================================
        // 5. orElseGet()
        // =====================================================

        // Supplier executes only when Optional is empty
        System.out.println("\n5. orElseGet()");

        String result3 = name1.orElseGet(() -> "Default Name");
        String result4 = name2.orElseGet(() -> "Default Name");

        System.out.println(result3); // Praveen
        System.out.println(result4); // Default Name


        // =====================================================
        // 6. orElseThrow()
        // =====================================================

        // Returns value or throws exception if empty
        System.out.println("\n6. orElseThrow()");

        String result5 = name1.orElseThrow(
                () -> new RuntimeException("Name not found"));

        System.out.println(result5);

        // Uncomment to test exception
        // name2.orElseThrow(
        //         () -> new RuntimeException("Name not found"));


        // =====================================================
        // 7. map()
        // =====================================================

        // Transforms the value inside Optional
        // Here String is converted into uppercase
        System.out.println("\n7. map()");

        Optional<String> upperName = name1.map(
                value -> value.toUpperCase());

        System.out.println(upperName); // Optional[PRAVEEN]

        // Another example: Multiply number by 2
        Optional<Integer> number = Optional.of(10);

        Optional<Integer> doubled = number.map(n -> n * 2);

        System.out.println(doubled); // Optional[20]


        // =====================================================
        // 8. flatMap()
        // =====================================================

        // Used when mapping function already returns Optional
        // Avoids nested Optional<Optional<String>>
        System.out.println("\n8. flatMap()");

        Optional<String> flatName = name1.flatMap(
                value -> Optional.of(value.toUpperCase()));

        System.out.println(flatName); // Optional[PRAVEEN]


        // =====================================================
        // map() vs flatMap()
        // =====================================================

        System.out.println("\n9. map() vs flatMap()");

        Optional<Optional<String>> mapResult =
                name1.map(value -> Optional.of(value.toUpperCase()));

        Optional<String> flatMapResult =
                name1.flatMap(value -> Optional.of(value.toUpperCase()));

        System.out.println("Map: " + mapResult);
        System.out.println("FlatMap: " + flatMapResult);
    }
}
