package com.java.p04_method_references;

import java.util.List;
import java.util.function.Function;

class Employee {

    private String name;

    // Constructor
    public Employee(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class ConstructorReferenceDemo {

    public static void main(String[] args) {

        //with lambda expression
        Function<String, Employee> employeeLambda =
                name -> new Employee(name);

        // Constructor reference
        Function<String, Employee> employeeFunction =
                Employee::new;

        Employee employee =
                employeeFunction.apply("Praveen");

        System.out.println(employee.getName());

        List<String> names = List.of("Amit", "Rahul", "Praveen");

        List<Employee> employees = names.stream()
                .map(Employee::new)
                .toList();
    }
}
