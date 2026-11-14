package com.java.p07_optional;

import java.util.Optional;

public class OptionalCreationDemo {

    public static void main(String[] args) {

        System.out.println("--- 1. Optional.of(value) ---");
        // Use this when you are 100% sure the value is NOT null.
        // If it is null, it throws a NullPointerException immediately.
        Optional<String> name = Optional.of("Alice");
        System.out.println("Value present: " + name.get()); // Alice

        // NOTE: If you try Optional.of(null), it will throw a NullPointerException:
        // Optional<String> crash = Optional.of(null); 


        System.out.println("\n--- 2. Optional.ofNullable(value) ---");
        // Use this when the value MIGHT be null. 
        // If it's null, it safely creates an empty Optional instead of crashing.
        
        String input = null;
        Optional<String> maybeName = Optional.ofNullable(input); // Safe!
        System.out.println("Is empty input present? " + maybeName.isPresent()); // false

        String validInput = "Bob";
        Optional<String> validMaybeName = Optional.ofNullable(validInput);
        System.out.println("Is valid input present? " + validMaybeName.isPresent()); // true


        System.out.println("\n--- 3. Optional.empty() ---");
        // Creates an explicitly empty box right away.
        Optional<String> emptyBox = Optional.empty();
        System.out.println("Is emptyBox present? " + emptyBox.isPresent()); // false
        
        // You can use orElse() to provide a fallback value if it's empty
        String result = emptyBox.orElse("Default Guest");
        System.out.println("Fallback result: " + result); // Default Guest
    }
}