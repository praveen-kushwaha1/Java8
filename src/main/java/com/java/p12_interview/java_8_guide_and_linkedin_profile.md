# Java 8 Interview Guide & Coding Solutions

## Java 8 Fundamentals & New Features

* What are the main new features introduced in Java 8?
* What is a Lambda Expression and what are its advantages?
* What is a Functional Interface?
* What is the `@FunctionalInterface` annotation?
* Can we extend one functional interface from another functional interface?
* Can a functional interface contain default and static methods?
* What are default methods in interfaces and why were they introduced?
* What are static methods in interfaces?
* What is multiple inheritance ambiguity in Java 8 interfaces and how is it resolved?
* What is a Method Reference and what are its different types?
* What is Type Inference in Java 8?

---

## Stream API Coding Questions

### Part 1: Easy Level Questions (1 – 15)

**1. Find all even numbers in a list:**
```java
List<Integer> numbers = List.of(10, 15, 8, 49, 25, 98, 32);
List<Integer> evens = numbers.stream()
        .filter(n -> n % 2 == 0)
        .toList();
```

**2. Find all numbers starting with 1:**
```java
List<Integer> numbers = List.of(10, 15, 8, 49, 125, 98);
List<String> startingWithOne = numbers.stream()
        .map(String::valueOf)
        .filter(s -> s.startsWith("1"))
        .toList();
```

**3. Find duplicate elements in an integer list:**
```java
List<Integer> list = List.of(10, 15, 8, 49, 15, 8);
Set<Integer> seen = new HashSet<>();
List<Integer> duplicates = list.stream()
        .filter(n -> !seen.add(n))
        .distinct()
        .toList();
```

**4. Find the first element of the list:**
```java
List<Integer> list = List.of(10, 15, 8, 49);
list.stream().findFirst().ifPresent(System.out::println);
```

**5. Find the total number of elements:**
```java
long count = list.stream().count();
```

**6. Find the maximum value element:**
```java
int max = list.stream().max(Integer::compare).orElseThrow();
```

**7. Find the minimum value element:**
```java
int min = list.stream().min(Integer::compare).orElseThrow();
```

**8. Sort a list of integers:**
```java
List<Integer> sorted = list.stream().sorted().toList();
```

**9. Sort a list in descending order:**
```java
List<Integer> sortedDesc = list.stream().sorted(Comparator.reverseOrder()).toList();
```

**10. Check if a list contains any element matching a condition (e.g., greater than 50):**
```java
boolean hasLarge = list.stream().anyMatch(n -> n > 50);
```

**11. Check if all elements match a condition (e.g., all positive):**
```java
boolean allPositive = list.stream().allMatch(n -> n > 0);
```

**12. Find the sum of all elements:**
```java
int sum = list.stream().mapToInt(Integer::intValue).sum();
```

**13. Find the average of all elements:**
```java
double avg = list.stream().mapToInt(Integer::intValue).average().orElse(0.0);
```

**14. Convert a list of strings to uppercase:**
```java
List<String> names = List.of("java", "stream", "api");
List<String> upperNames = names.stream().map(String::toUpperCase).toList();
```

**15. Limit a stream to the first $N$ elements:**
```java
List<Integer> firstThree = list.stream().limit(3).toList();
```

---

### Part 2: Intermediate Level Questions (16 – 30)

**16. Skip the first $N$ elements of a stream:**
```java
List<Integer> skipped = list.stream().skip(2).toList();
```

**17. Find the second-largest element in a list:**
```java
int secondLargest = list.stream()
        .distinct()
        .sorted(Comparator.reverseOrder())
        .skip(1)
        .findFirst()
        .orElseThrow();
```

**18. Group a list of strings by their length:**
```java
List<String> words = List.of("apple", "banana", "cat", "dog", "elephant");
Map<Integer, List<String>> groupedByLength = words.stream()
        .collect(Collectors.groupingBy(String::length));
```

**19. Partition a list of integers into even and odd numbers:**
```java
Map<Boolean, List<Integer>> partitioned = numbers.stream()
        .collect(Collectors.partitioningBy(n -> n % 2 == 0));
```

**20. Count occurrences of each character in a string:**
```java
String str = "javaprogramming";
Map<Character, Long> charCount = str.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
```

**21. Count occurrences of each word in a sentence:**
```java
String sentence = "apple banana apple orange banana apple";
Map<String, Long> wordCount = Arrays.stream(sentence.split(" "))
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
```

**22. Merge two lists into a single combined list:**
```java
List<String> list1 = List.of("A", "B");
List<String> list2 = List.of("C", "D");
List<String> merged = Stream.concat(list1.stream(), list2.stream()).toList();
```

**23. Find the intersection of two lists:**
```java
List<Integer> l1 = List.of(1, 2, 3, 4);
List<Integer> l2 = List.of(3, 4, 5, 6);
List<Integer> intersection = l1.stream()
        .filter(l2::contains)
        .toList();
```

**24. Remove duplicate elements while preserving order:**
```java
List<Integer> uniqueList = list.stream().distinct().toList();
```

**25. Find strings containing a specific letter (e.g., 'a'):**
```java
List<String> containsA = words.stream()
        .filter(w -> w.contains("a"))
        .toList();
```

**26. Concatenate a list of strings with a delimiter:**
```java
String result = words.stream().collect(Collectors.joining(", "));
```

**27. Square numbers and filter values greater than 50:**
```java
List<Integer> processed = List.of(2, 5, 8, 10).stream()
        .map(n -> n * n)
        .filter(n -> n > 50)
        .toList();
```

**28. Flatten a list of lists (`List<List<Integer>>`) into a single list:**
```java
List<List<Integer>> nested = List.of(List.of(1, 2), List.of(3, 4));
List<Integer> flattened = nested.stream()
        .flatMap(List::stream)
        .toList();
```

**29. Find the length of the longest string in a list:**
```java
int maxLength = words.stream()
        .mapToInt(String::length)
        .max()
        .orElse(0);
```

**30. Extract a specific property from a list of objects into a new list:**
```java
// Assuming an Employee class with getName()
List<String> namesList = employees.stream()
        .map(Employee::getName)
        .toList();
```

---

### Part 3: Advanced Level Questions (31 – 41)

**31. Find the first non-repeated character in a string:**
```java
String s = "swiss";
Character firstNonRep = s.chars()
        .mapToObj(c -> (char) c)
        .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
        .entrySet().stream()
        .filter(entry -> entry.getValue() == 1)
        .map(Map.Entry::getKey)
        .findFirst()
        .orElse(null);
```

**32. Find the first repeated character in a string:**
```java
Set<Character> seenChars = new HashSet<>();
Character firstRep = s.chars()
        .mapToObj(c -> (char) c)
        .filter(c -> !seenChars.add(c))
        .findFirst()
        .orElse(null);
```

**33. Check if a string is a palindrome using streams:**
```java
String text = "madam";
boolean isPalindrome = IntStream.range(0, text.length() / 2)
        .allMatch(i -> text.charAt(i) == text.charAt(text.length() - 1 - i));
```

**34. Find the most frequent element in a list:**
```java
String mostFrequent = words.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream()
        .max(Map.Entry.comparingByValue())
        .map(Map.Entry::getKey)
        .orElse(null);
```

**35. Generate a Fibonacci sequence of $N$ numbers:**
```java
List<Long> fibonacci = Stream.iterate(new long[]{0, 1}, f -> new long[]{f[1], f[0] + f[1]})
        .limit(10)
        .map(f -> f[0])
        .toList();
```

**36. Compute the sum of digits of a number using streams:**
```java
int number = 456;
int digitSum = String.valueOf(number).chars()
        .map(Character::getNumericValue)
        .sum();
```

**37. Group employees by department and find the highest-paid employee in each department:**
```java
Map<String, Optional<Employee>> topPaidByDept = employees.stream()
        .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
        ));
```

**38. Partition employees into those earning above vs. below a salary threshold ($50,000):**
```java
Map<Boolean, List<Employee>> partitionedBySalary = employees.stream()
        .collect(Collectors.partitioningBy(e -> e.getSalary() > 50000));
```

**39. Find top 3 longest strings in a list:**
```java
List<String> top3 = words.stream()
        .sorted(Comparator.comparing(String::length).reversed())
        .limit(3)
        .toList();
```

**40. Map a list of objects into a custom Map (handling duplicate keys safely):**
```java
Map<Integer, String> idToNameMap = employees.stream()
        .collect(Collectors.toMap(
                Employee::getId,
                Employee::getName,
                (existing, replacement) -> existing // Merge rule if duplicate key occurs
        ));
```

**41. Calculate word frequency distribution sorted by count descending:**
```java
Map<String, Long> sortedFrequency = words.stream()
        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
        .entrySet().stream()
        .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
        .collect(Collectors.toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (e1, e2) -> e1,
                LinkedHashMap::new
        ));
```

---

## Conceptual Questions by Topic

### Stream API
* What is the Stream API in Java 8?
* What is the difference between a Collection and a Stream?
* What is the difference between intermediate and terminal operations in Streams?
* What is Lazy Evaluation in Java 8 Streams?
* How do `map()` and `flatMap()` differ from each other?
* What is the difference between `findFirst()` and `findAny()`?
* What is the difference between `anyMatch()`, `allMatch()`, and `noneMatch()`?
* How does `peek()` differ from `forEach()`?
* What is the difference between `limit()` and `skip()`?
* How do you find duplicate elements in a List using Streams?
* How do you find the maximum and minimum elements in a Stream?
* How do you sort elements using Java 8 Streams (ascending and descending)?
* How do you count the occurrences of each word/character in a String using Streams?
* How do you group elements using `Collectors.groupingBy()`?
* How do you partition elements using `Collectors.partitioningBy()`?
* What is the purpose of `Collectors.mapping()` and `Collectors.reducing()`?
* How do you handle infinite streams using `Stream.iterate()` or `Stream.generate()`?
* How do you convert a List into a Map using Streams (handling duplicate keys)?

### Optional Class
* What is the `Optional` class in Java 8 and why was it introduced?
* How does `Optional` help in avoiding `NullPointerException`?
* What is the difference between `of()`, `ofNullable()`, and `empty()` in `Optional`?
* What is the difference between `get()`, `orElse()`, `orElseGet()`, and `orElseThrow()`?
* Can we use `Optional` as field types in entities or method arguments? (Best practices)

### Functional Interfaces (`java.util.function`)
* What are the core functional interfaces provided in the `java.util.function` package?
* What is the difference between `Predicate` and `Function`?
* What is the purpose of `Consumer` and `Supplier`?
* What is a `BiFunction`, `BiPredicate`, and `BiConsumer`?
* What is the difference between `UnaryOperator` and `BinaryOperator`?
* How do you perform `Predicate` chaining (`and()`, `or()`, `negate()`)?
* How do you compose and then apply functions (`compose()` vs `andThen()`)?

### Date and Time API (JSR-310)
* Why was the old `java.util.Date` and `Calendar` API replaced in Java 8?
* What are the key classes in the new Date-Time API (`LocalDate`, `LocalTime`, `LocalDateTime`)?
* How do you calculate the difference between two dates using `Period` and `Duration`?
* How do you handle time zones using `ZonedDateTime`?
* How do you format and parse dates using `DateTimeFormatter`?

### Miscellaneous / Advanced Java 8 Topics
* What is a `Spliterator` and how does it work?
* What is Nashorn JavaScript Engine introduced in Java 8?
* What are Parallel Streams and when should you use them (vs sequential streams)?
* What are some common performance pitfalls or interview traps when using Java 8 Streams?

---

# LinkedIn Profile / Job Search Post

🚀 **OPEN TO WORK | IMMEDIATE JOINER | JAVA BACKEND DEVELOPER | SOFTWARE ENGINEER**

Hello LinkedIn Network! 👋

I'm excited to share that I'm actively exploring new career opportunities as a **Java Backend Developer / Software Engineer** and am available to join immediately.

With **3+ years of experience in software development**, I have hands-on experience building, enhancing, and maintaining enterprise-level applications, scalable microservices, and RESTful APIs, primarily in the Banking and Financial Services domain.

### 💻 TECHNICAL SKILLS

* **Programming Languages:** Java 8+, Kotlin, SQL  
* **Backend Technologies:** Spring Boot, Spring MVC, Spring WebFlux, Spring Security, Hibernate, JPA, JDBC  
* **Microservices:** Microservices Architecture, Spring Cloud, REST APIs, API Gateway, Service Discovery, Feign Client  
* **Messaging:** Apache Kafka, Event-Driven Architecture  
* **Databases:** MySQL, Oracle, SQL Server, Redis  
* **Search & Monitoring:** Elasticsearch, ELK Stack, Kibana, Prometheus, Grafana, Zipkin  
* **Security:** JWT, OAuth2, Role-Based Access Control (RBAC)  
* **Testing:** JUnit, Mockito, Postman, Swagger  
* **DevOps & Tools:** Git, GitHub, Docker, Jenkins, Maven, SonarQube, Jira

### 🚀 EXPERIENCE HIGHLIGHTS

* Developed and maintained scalable backend services and microservices for enterprise banking applications.
* Designed and developed RESTful APIs for customer management, account services, card management, and fund transfer workflows.
* Worked with Java 8, Spring Boot, Spring Data JPA, Hibernate, and SQL to build robust backend solutions.
* Implemented Kafka-based producer and consumer workflows for asynchronous communication between microservices.
* Implemented secure authentication and authorization using Spring Security, JWT, and OAuth2.
* Worked on application performance optimization, database query improvements, and troubleshooting production issues.
* Integrated monitoring, logging, and distributed tracing tools to improve application observability and debugging.
* Collaborated with cross-functional teams in Agile/Scrum environments to deliver quality software across releases.

### 🎯 LOOKING FOR

Opportunities in:
* Java Backend Developer
* Java Software Engineer
* Spring Boot Developer
* Java Microservices Developer
* Backend Software Development Engineer (SDE)

📍 **Preferred Locations:** Bangalore | Hyderabad | Noida | Remote  
🏢 **Work Mode:** Hybrid | Remote | On-site  
✅ **Availability:** Immediate Joiner  

If you know of any relevant opportunities or are hiring for Java Backend Developer roles, I would genuinely appreciate your support, referrals, and connections.

Feel free to connect with me or reach out directly.

Thank you for your support! 🙌

`#OpenToWork` `#ImmediateJoiner` `#JavaDeveloper` `#JavaBackendDeveloper` `#SpringBoot` `#Microservices` `#SoftwareEngineer` `#BackendDeveloper` `#Java8` `#SpringSecurity` `#Hibernate` `#JPA` `#ApacheKafka` `#RESTAPI` `#SQL` `#MySQL` `#Redis` `#Elasticsearch` `#Docker` `#Jenkins` `#BangaloreJobs` `#HyderabadJobs` `#Hiring`