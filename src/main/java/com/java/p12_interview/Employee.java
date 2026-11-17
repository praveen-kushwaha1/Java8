package com.java.p12_interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Employee {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1,2,3,4,4,4,5,5,5,6);

        Set<Integer> seen=new HashSet<>();

        numbers.stream()
                .filter(n->!seen.add(n))
                .forEach(System.out::println);


    }


    private String name;
    private Double salary;
    private String department;

    public Employee(String name, double salary,String department) {
        this.name = name;
        this.salary = salary;
        this.department=department;
    }



    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", department='" + department + '\'' +
                '}';
    }

}