package com.java.p03_comparable_comparator.comparable;

import java.util.*;

/**
 * ==========================================================
 * CONCEPT: Comparable (Natural Sorting)
 * ==========================================================
 * - The Employee class implements Comparable<Employee> to define
 *   its own "natural" or default sorting order.
 * - Here, natural sorting is defined based on the Employee's ID.
 */
class Employee implements Comparable<Employee> {

    private int id;
    private String name;
    private double salary;

    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    /**
     * Overrides compareTo() from the Comparable interface.
     *
     * @param other the object to be compared with this object.
     * @return
     *   - Negative integer if (this < other)
     *   - Zero if (this == other)
     *   - Positive integer if (this > other)
     *
     * Best Practice: Use Integer.compare() to avoid manual subtraction
     * integer overflow bugs.
     */
    @Override
    public int compareTo(Employee other) {
        // Natural sorting order: ascending by ID
        return Integer.compare(this.id, other.id);

        // Descending order alternative:
        // return Integer.compare(other.id, this.id);
    }

    @Override
    public String toString() {
        return id + " " + name + " " + salary;
    }
}

public class ComparableDemo {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(3, "Neha", 40000));
        employees.add(new Employee(1, "Rahul", 50000));
        employees.add(new Employee(2, "Amit", 70000));

        System.out.println("--- NATURAL SORTING (BY ID) ---");

        // Collections.sort() automatically invokes Employee's compareTo() method
        // because Employee implements Comparable.
        Collections.sort(employees);

        // Print sorted employees using method reference
        employees.forEach(System.out::println);
    }
}