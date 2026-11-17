package com.java.p12_interview.easy;

import com.java.p12_interview.Employee;

import java.util.*;
import java.util.stream.Collectors;


public class CodenCodepart1 {
    public static void main(String[] args) {
        System.out.println("=== Top 20 Java 8 Stream API Interview Operations & Logic Revision ===\n");

        // ==========================================
        // 1. FILTER: Conditional Data Extraction
        // ==========================================
        // LOGIC: filter() takes a Predicate (boolean condition). If true, the element 
        // moves down the stream. If false, it drops out.
        List<Integer> numbers = Arrays.asList(2, 4, 6, 8, 1, 3, 10, 15, 49, 25, 98, 32);
        List<Integer> filtered = numbers.stream()
                .filter(n -> n > 5) // Keep numbers strictly greater than 5
                .collect(Collectors.toList());
        System.out.println("1. filter (> 5): " + filtered);

        List<Integer> evenList = numbers.stream()
                .filter(n -> n % 2 == 0) // Keep numbers divisible by 2 (Even)
                .collect(Collectors.toList());
        System.out.println("   Even numbers: " + evenList);

        // ==========================================
        // 2. MAP: Transforming Data Elements
        // ==========================================
        // LOGIC: map() takes a Function. It transforms every input element into a new output element 
        // (e.g., Object to String, or lowercase to uppercase).
        List<String> names = Arrays.asList("john", "doe", "smith");
        List<String> upperNames = names.stream()
                .map(String::toUpperCase) // Transforms each string item to uppercase
                .collect(Collectors.toList());
        System.out.println("2. map (to uppercase): " + upperNames);

        // Interview Trick: Convert Integer to String first, then filter by prefix
        List<Integer> listWithPrefix = Arrays.asList(10, 15, 8, 49, 25, 98, 112);
        List<String> resultPrefix = listWithPrefix.stream()
                .map(String::valueOf)         // Convert Integer -> String
                .filter(s -> s.startsWith("1")) // Check string prefix condition
                .collect(Collectors.toList());
        System.out.println("   Starts with '1': " + resultPrefix);

        resultPrefix.stream()
                .map(i->i.toUpperCase())
                .filter(s->s.startsWith("1"))
                .forEach(System.out::println);

        // ==========================================
        // 3. FLATMAP: Flattening Nested Collections
        // ==========================================
        // LOGIC: Used when elements themselves contain streams/lists (List<List<T>>). 
        // It flattens multi-level lists into a single flat stream of elements.
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d")
        );
        List<String> flatList = listOfLists.stream()
                .flatMap(List::stream) // Opens each inner list and merges elements into one stream
                .collect(Collectors.toList());
        listOfLists.stream()
                        .flatMap(i->i.stream())
                        .forEach(System.out::println);
        System.out.println("3. flatMap: " + flatList);

        // ==========================================
        // 4. FIND DUPLICATES (HashSet Trick)
        // ==========================================
        // LOGIC: HashSet.add(item) returns true if item is new, and false if item already exists.
        // '!seen.add(n)' means: "Keep it only if the add() operation fails (i.e., it's a duplicate!)"
        List<Integer> duplicateListInput = Arrays.asList(10, 15, 8, 49, 25, 98, 98,98, 32, 15);
        Set<Integer> seen = new HashSet<>();
        List<Integer> duplicates = duplicateListInput.stream()
                .filter(n -> !seen.add(n)) 
                .collect(Collectors.toList());
        System.out.println("4. Duplicate elements: " + duplicates);

        // ==========================================
        // 5. DISTINCT: Removing Duplicates Easily
        // ==========================================
        // LOGIC: Uses object equals() and hashCode() behind the scenes to filter out duplicate items.
        List<Integer> duplicatesInput = Arrays.asList(1, 2, 2, 3, 4, 4, 5);
        List<Integer> distinctList = duplicatesInput.stream()
                .distinct() // Removes all repeated values automatically
                .collect(Collectors.toList());
        System.out.println("5. distinct: " + distinctList);

        // ==========================================
        // 6. SORTED: Natural & Reverse Ordering
        // ==========================================
        // LOGIC: sorted() arranges elements. Default is natural order. 
        // Pass 'Comparator.reverseOrder()' for descending/reverse sorting.
        List<String> unsortedNames = Arrays.asList("Alice", "Bob", "Charlie", "David");
        List<String> sortedReverse = unsortedNames.stream()
                .sorted(Comparator.reverseOrder()) // Z to A ordering
                .collect(Collectors.toList());
        System.out.println("6. sorted (reverse alphabetical): " + sortedReverse);

        List<Integer> listAsc = Arrays.asList(10, 15, 8, 49, 25, 98);
        System.out.print("   Sorted ascending: ");
        listAsc.stream().sorted().forEach(n -> System.out.print(n + " "));
        System.out.println();

        // ==========================================
        // 7. PEEK: Debugging & Side Effects
        // ==========================================
        // LOGIC: Allows you to perform an action (like printing) on each element as it passes through 
        // the stream pipeline without breaking or modifying the stream flow. Great for debugging!
        System.out.print("7. peek: ");
        List<String> peeked = names.stream()
                .peek(n -> System.out.print("[" + n + "] ")) // Inspects items inline
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println("\n   Result: " + peeked);

        // ==========================================
        // 8. LIMIT: Restricting Stream Size
        // ==========================================
        // LOGIC: Short-circuiting operation that truncates the stream to a maximum specified size.
        List<Integer> firstThree = numbers.stream()
                .limit(3) // Grabs only the first 3 elements and stops processing
                .collect(Collectors.toList());
        System.out.println("8. limit (first 3): " + firstThree);

        // ==========================================
        // 9. SKIP: Bypassing Elements
        // ==========================================
        // LOGIC: Discards the first N elements of the stream and lets the remaining elements pass through.
        List<Integer> skipped = numbers.stream()
                .skip(4) // Drops the first 4 elements, keeps elements from index 4 onwards
                .collect(Collectors.toList());
        System.out.println("9. skip (first 4): " + skipped);

        // ==========================================
        // 10. FOREACH: Terminal Iteration
        // ==========================================
        // LOGIC: A terminal operation that loops through each element in the stream to perform an action.
        System.out.print("10. forEach: ");
        names.stream().forEach(n -> System.out.print("Item: " + n + " "));
        System.out.println();

        // ==========================================
        // 11. COLLECT & COUNT: Aggregating Results
        // ==========================================
        // LOGIC: collect() packs stream results into Collections (List, Set, Map). 
        // count() returns a primitive long representing total elements.
        Set<Integer> numberSet = numbers.stream()
                .collect(Collectors.toSet()); // Removes duplicates and stores in a Set
        System.out.println("11. collect (into Set): " + numberSet);
        System.out.println("    Total count: " + numbers.stream().count());

        // ==========================================
        // 12. REDUCE: Combining Elements Accumulatively
        // ==========================================
        // LOGIC: Takes an initial identity value and a binary operator function to combine elements 
        // step-by-step (e.g., finding sums, products, or concatenations).
        int product = Arrays.asList(1, 2, 3, 4).stream()
                .reduce(1, (a, b) -> a * b); // 1*1=1 -> 1*2=2 -> 2*3=6 -> 6*4=24
        System.out.println("12. reduce (product): " + product);

        // ==========================================
        // 13. MAX & CUSTOM OBJECT COMPARISON
        // ==========================================
        // LOGIC: Finds the maximum element based on a Comparator rule. 
        // 'Comparator.comparing(Employee::getSalary)' extracts the salary field for numerical comparison.
        int max = numbers.stream().max(Integer::compare).get();
        System.out.println("13. Max value: " + max);

        List<Employee> employeeList = Arrays.asList(
            new Employee("Alice", 45000,"HR"),
            new Employee("Bob", 95000,"ISS"),
            new Employee("Charlie", 70000,"OS")
        );
        Employee highestPaid = employeeList.stream()
                .max(Comparator.comparing(Employee::getSalary)) // Compares objects by salary getter
                .orElse(null);
        System.out.println("    Highest paid employee: " + highestPaid);

        // ==========================================
        // 14. FIND SECOND-HIGHEST NUMBER (Interview Classic)
        // ==========================================
        // LOGIC: distinct() removes duplicates -> sorted(reverseOrder) puts highest first -> 
        // skip(1) drops the absolute highest -> findFirst() grabs the second highest.
        List<Integer> secondHighInput = Arrays.asList(5, 1, 2, 3, 4, 5, 6);
        Integer secondHighest = secondHighInput.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1) // Skips the 1st highest element (6)
                .findFirst() // Grabs the next element (5)
                .get();
        System.out.println("14. Second highest number: " + secondHighest);

        // ==========================================
        // 15. GROUPINGBY: Frequency & Categorization Maps
        // ==========================================
        // LOGIC: Groups stream elements into a Map based on a classification function, 
        // often paired with downstream collectors like counting().
        List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana");
        Map<String, Long> freqMap = words.stream()
                .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        System.out.println("15. Frequency map: " + freqMap);

        // ==========================================
        // 16. FIRST NON-REPEATED CHARACTER (String Stream)
        // ==========================================
        // LOGIC: string.chars() makes an IntStream of characters -> mapToObj casts to Character -> 
        // groupingBy with LinkedHashMap preserves insertion order -> filter elements with count == 1.
        String str = "swiss";
        Character firstNonRepeated = str.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1) // Keep elements that occur only once
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        System.out.println("16. First non-repeated character: " + firstNonRepeated);

        // ==========================================
        // 17. COLLECTORS.JOINING: String Stitching
        // ==========================================
        // LOGIC: Efficiently concatenates stream string elements together with optional delimiters, prefixes, and suffixes.
        List<String> namesJoined = Arrays.asList("ram", "shyam", "mohan");
        String joinedStr = namesJoined.stream()
                .sorted()
                .collect(Collectors.joining(", ")); // Joins sorted elements separated by comma and space
        System.out.println("17. Joined string: " + joinedStr);

        // ==========================================
        // 18. PARTITIONINGBY: Boolean Two-Way Split
        // ==========================================
        // LOGIC: Special case of grouping that splits a collection into a Map<Boolean, List<T>> 
        // based on whether a predicate evaluates to true or false.
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0)); // true = even, false = odd
        System.out.println("18. Partitioned map (even/odd): " + partitioned);

        // ==========================================
        // 19. MATCHING OPERATIONS: allMatch, anyMatch, noneMatch
        // ==========================================
        // LOGIC: Short-circuiting terminal operations that evaluate predicates and return a single boolean.
        List<Integer> listMatch = Arrays.asList(10, 20, 30);
        boolean allPositive = listMatch.stream().allMatch(n -> n > 0);   // True if ALL elements > 0
        boolean anyEven = listMatch.stream().anyMatch(n -> n % 2 == 0);   // True if AT LEAST ONE is even
        boolean noneNegative = listMatch.stream().noneMatch(n -> n < 0); // True if NO element is negative
        System.out.println("19. allPositive: " + allPositive + ", anyEven: " + anyEven + ", noneNegative: " + noneNegative);

        // ==========================================
        // 20. FINDFIRST: Safe Optional Element Retrieval
        // ==========================================
        // LOGIC: Returns an Optional describing the first element of the stream, 
        // preventing NullPointerExceptions if the collection is empty.
        Optional<Integer> first = numbers.stream().findFirst();
        System.out.print("20. findFirst element: ");
        first.ifPresent(System.out::println); // Executes action only if value is present

        //20. FINDANY: Returns an Optional describing any element of the stream, useful for parallel streams.
        Optional<Integer> any = numbers.stream().findAny();
        names.stream()
                        .filter(n->n.startsWith("j"))
                        .findAny()
                        .ifPresent(System.out::println);
        System.out.println("   findAny element: " + any.orElse(null));
        System.out.print("    findAny element: ");
        any.ifPresent(System.out::println); // Executes action only if value is present

        //==========================================
        //MAX and MIN
        //==========================================
        numbers.stream()
                .max(Integer::compareTo)
                .ifPresent(maxx -> System.out.println("Max value: " + maxx));

        numbers.stream()
                .min(Integer::compareTo)
                .ifPresent(min -> System.out.println("Min value: " + min));


        //toArray
        //convert list of string to array of string
        List<Integer> numberList=Arrays.asList(1,3,4,4,5);

        Integer[] numberArray = numberList.stream()
                .toArray(Integer[]::new);


        //joining
        // element of string into single string

        String joinedNames = names.stream()
                .collect(Collectors.joining(", "));
        String joinedNames1 = names.stream()
                .collect(Collectors.joining(", ","[" ,"]"));
        System.out.println("Joined names: " + joinedNames);
        System.out.println("Joined names with brackets: " + joinedNames1);

    }

}