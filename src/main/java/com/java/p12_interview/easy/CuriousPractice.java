package com.java.p12_interview.easy;

import com.java.p12_interview.Employee;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Java 8 Stream API - Interview Practice & Revision Notes
 * Covers: Filtering, Mapping, Sorting, Grouping, Partitioning, Flattening, and Parallel Streams.
 */
public class CuriousPractice {

    public static void main(String[] args) {

        // Sample Data
        List<Integer> numbers = Arrays.asList(10, 15, 8, 6, 3, 3, 3, 4, 4, 2);
        List<String> names = Arrays.asList("Praveen", "Harshit", "Kumkum", "Praveen");

        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Praveen", 11, "HR"));
        employeeList.add(new Employee("Hraveen", 1, "HR"));
        employeeList.add(new Employee("Jraveen", 12, "ISS"));
        employeeList.add(new Employee("Traveen", 19, "OS"));


        // =========================================================================
        // 1. BASIC STREAM OPERATIONS (Filter, Map, Distinct, Sort, Skip, Limit)
        // =========================================================================

        // Q1. Remove duplicates & sort in descending order
        // Note: distinct() uses equals() and hashCode() under the hood.
        List<Integer> uniqueDesc = numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Q1 (Unique Desc): " + uniqueDesc);


        // Q2. Filter odd numbers & square them
        List<Integer> squaredOdds = numbers.stream()
                .filter(i -> i % 2 != 0)
                .map(i -> i * i)
                .collect(Collectors.toList());
        System.out.println("Q2 (Squared Odds): " + squaredOdds);


        // Q3. Find the 2nd and 3rd elements
        // Note: skip(n) discards first n elements; limit(m) takes next m elements.
        List<Integer> secondAndThird = numbers.stream()
                .skip(1)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("Q3 (2nd & 3rd Elements): " + secondAndThird);


        // =========================================================================
        // 2. PARTITIONING & GROUPING (Collectors.partitioningBy & groupingBy)
        // =========================================================================

        // Q4. Partition a list into even and odd
        // Note: partitioningBy ALWAYS returns a Map<Boolean, List<T>> with both true & false keys.
        Map<Boolean, List<Integer>> evenOddMap = numbers.stream()
                .collect(Collectors.partitioningBy(i -> i % 2 == 0));
        System.out.println("Q4 (Even/Odd Partition): " + evenOddMap);


        // Q5. Group words by their length
        Map<Integer, List<String>> wordsByLength = names.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println("Q5 (Grouped by Length): " + wordsByLength);


        // Q6. Count word frequency in a list
        // Tip: Function.identity() is a cleaner alternative to (name -> name)
        Map<String, Long> wordFrequency = names.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Q6 (Word Frequency): " + wordFrequency);


        // =========================================================================
        // 3. FLATTENING NESTED COLLECTIONS (flatMap)
        // =========================================================================

        List<List<Integer>> nestedList = Arrays.asList(
                Arrays.asList(1, 2, 34),
                Arrays.asList(1, 34)
        );

        // Q7. Flatten a list of lists
        // Note: map() transforms 1-to-1; flatMap() transforms 1-to-Many and flattens streams into one.
        List<Integer> flattenedList = nestedList.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println("Q7 (Flattened List): " + flattenedList);


        // Q8. Flatten a list of lists & remove duplicates
        List<Integer> uniqueFlattened = nestedList.stream()
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Q8 (Unique Flattened): " + uniqueFlattened);


        // =========================================================================
        // 4. EMPLOYEE ANALYTICS (Custom Objects, Comparators & Downstream Collectors)
        // =========================================================================

        // Q9. Find the employee with the highest salary
        // Approach A: Using sorted().findFirst() -> O(N log N)
        Optional<Employee> highestPaidSort = employeeList.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .findFirst();

        // Approach B (Interview Pro-Tip): Using max() -> O(N) - Faster, avoids full sorting!
        Optional<Employee> highestPaidMax = employeeList.stream()
                .max(Comparator.comparingDouble(Employee::getSalary));
        System.out.println("Q9 (Highest Paid): " + highestPaidMax.orElse(null));


        // Q10. Get the top 3 highest-paid employees
        System.out.print("Q10 (Top 3 Paid): ");
        employeeList.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(3)
                .forEach(emp -> System.out.print(emp.getName() + " "));
        System.out.println();


        // Q11. Find the total salary by department
        Map<String, Double> totalSalaryByDept = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.summingDouble(Employee::getSalary)
                ));
        System.out.println("Q11 (Total Salary by Dept): " + totalSalaryByDept);


        // Q12. Count how many employees are in each department
        Map<String, Long> empCountByDept = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.counting()
                ));
        System.out.println("Q12 (Employee Count by Dept): " + empCountByDept);


        // Q13. Find the average salary of employees in each department
        Map<String, Double> avgSalaryByDept = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        System.out.println("Q13 (Avg Salary by Dept): " + avgSalaryByDept);


        // Q14. Find the highest-paid employee in each department
        Map<String, Optional<Employee>> highestPaidByDept = employeeList.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
                ));
        System.out.println("Q14 (Highest Paid by Dept): " + highestPaidByDept);


        // =========================================================================
        // 5. STRING JOINING & SET INTERSECTION
        // =========================================================================

        // Q15. Convert a list of names into a comma-separated string
        // Note: Collectors.joining(delimiter, prefix, suffix) is also available!
        String joinedNames = names.stream()
                .collect(Collectors.joining(", "));
        System.out.println("Q15 (Joined Names): " + joinedNames);


        // Q16. Find common elements between two lists
        List<Integer> commonList1 = Arrays.asList(1, 2, 3, 4, 4);
        List<Integer> commonList2 = Arrays.asList(11, 22, 33, 42, 4);

        // Interview Pro-Tip: Convert commonList2 to a HashSet first for O(1) lookups instead of O(N) List.contains()
        Set<Integer> lookupSet = new HashSet<>(commonList2);
        List<Integer> commonElements = commonList1.stream()
                .filter(lookupSet::contains)
                .distinct() // Optional: removes duplicate '4' from result
                .collect(Collectors.toList());
        System.out.println("Q16 (Common Elements): " + commonElements);


        // =========================================================================
        // 6. PARALLEL STREAMS (Multi-threading with ForkJoinPool)
        // =========================================================================

        // Q17. Print all employee names as fast as possible
        // Warning: parallelStream().forEach() does NOT guarantee encounter order!
        // Use forEachOrdered() if order matters.
        System.out.print("Q17 (Parallel Print): ");
        employeeList.parallelStream()
                .forEach(emp -> System.out.print(emp.getName() + " "));
        System.out.println();


        // Q18. Compute total salary of all employees using parallel stream
        // Note: mapToDouble().sum() avoids autoboxing overhead of Double wrapper objects.
        double totalSalary = employeeList.parallelStream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println("Q18 (Parallel Total Salary): " + totalSalary);
    }
}