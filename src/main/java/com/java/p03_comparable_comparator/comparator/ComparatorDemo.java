package com.java.p03_comparable_comparator.comparator;

import java.util.*;

/**
 * ==========================================================
 * CONCEPT: Comparator (Custom / External Sorting)
 * ==========================================================
 * - The Employee class does NOT implement Comparable. It remains clean
 *   and uncoupled from any specific sorting logic.
 * - Sorting behavior is supplied externally via a Comparator instance.
 */
class Employee {

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

    @Override
    public String toString() {
        return id + " " + name + " " + salary;
    }
}

public class ComparatorDemo {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee(3, "Neha", 40000));
        employees.add(new Employee(1, "Rahul", 50000));
        employees.add(new Employee(2, "Amit", 70000));

        System.out.println("--- CUSTOM SORTING (BY SALARY) ---");

        // Java 8 Lambda Expression for Comparator:
        // Compares two Employee objects (e1 and e2) based on their salary.
        // Double.compare() safely handles double primitive comparisons.
        Comparator<Employee> salaryComparator =
                (e1, e2) -> Double.compare(
                        e1.getSalary(), e2.getSalary()
                );

        // Pass both the list and the custom comparator to Collections.sort()
        Collections.sort(employees, salaryComparator);

        // Output sorted list
        employees.forEach(System.out::println);

        // Modern List.sort() alternative (introduced in Java 8):
        // employees.sort(salaryComparator);
    }
}