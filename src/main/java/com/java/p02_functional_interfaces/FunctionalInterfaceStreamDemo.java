package com.java.p02_functional_interfaces;

import java.util.List;
import java.util.function.Predicate;
import java.util.function.Function;
import java.util.function.Consumer;

public class FunctionalInterfaceStreamDemo {

    public static void main(String[] args) {

        List<Integer> salaries =
                List.of(20000, 30000, 50000, 60000, 80000);

        // STEP 1: Predicate
        // Check whether salary is greater than 40000
        Predicate<Integer> highSalary =
                salary -> salary > 40000;

        // STEP 2: Function
        // Increase salary by 10%
        Function<Integer, Integer> incrementSalary =
                salary -> salary + (salary * 10 / 100);

        // STEP 3: Consumer
        // Print final salary
        Consumer<Integer> printSalary =
                salary -> System.out.println(
                        "Updated Salary: " + salary
                );

        // STEP 4: Stream processing
        salaries.stream()

                // Filter salaries greater than 40000
                .filter(highSalary)

                // Increase salary by 10%
                .map(incrementSalary)

                // Print updated salary
                .forEach(printSalary);

        salaries.stream()
                .filter(salarie->salarie>30000)
                .map(salary->salary*2)
                .forEach(salary->System.out.println("Updated Salary: " + salary));
    }
}