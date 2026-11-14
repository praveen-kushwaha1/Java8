# Java 8 – Functional Interfaces (Complete Detailed Notes)

Functional Interfaces are one of the most important concepts introduced in Java 8. They are the foundation of:

1. Lambda Expressions
2. Method References
3. Stream API
4. Functional Programming
5. Collections processing
6. Spring Boot and modern Java development

We will understand all 9 functional interfaces from your folder structure using simple real-world examples, runnable Java code, detailed comments, and interview points.

---

## 1. What is a Functional Interface?

A **Functional Interface** is an interface that contains **exactly one abstract method**.  
It is also called a **SAM Interface** (*Single Abstract Method*).

### Example

```java
@FunctionalInterface
interface Calculator {

    // Only one abstract method
    int add(int a, int b);

}
```

Here:
* `Calculator` is a functional interface.
* `add()` is its only abstract method.
* `@FunctionalInterface` tells the compiler to verify the interface.

### Why do we need Functional Interfaces?

Before Java 8, we generally implemented interfaces using anonymous inner classes.

**Before Java 8:**

```java
Calculator calculator = new Calculator() {

    @Override
    public int add(int a, int b) {
        return a + b;
    }
};

System.out.println(calculator.add(10, 20));
```

**Java 8 – Using Lambda Expression:**

```java
Calculator calculator = (a, b) -> a + b;

System.out.println(calculator.add(10, 20));
```

**Output:**
```text
30
```

Lambda expressions provide a shorter way to implement functional interfaces.

### Important Rules
* Exactly one abstract method is allowed.
* Multiple `default` methods are allowed.
* Multiple `static` methods are allowed.
* Methods inherited from `Object` do not count as additional abstract methods.
* `@FunctionalInterface` is optional but recommended.

---

## 2. Understanding all 9 Functional Interfaces

Java provides several built-in functional interfaces in the `java.util.function` package. Let's understand each one.

### Functional Interfaces – Quick Revision

| Interface | Input | Output | Main method |
| :--- | :--- | :--- | :--- |
| **Predicate** | `T` | `boolean` | `test()` |
| **Function** | `T` | `R` | `apply()` |
| **Consumer** | `T` | `void` | `accept()` |
| **Supplier** | None | `T` | `get()` |
| **BiPredicate** | `T, U` | `boolean` | `test()` |
| **BiFunction** | `T, U` | `R` | `apply()` |
| **BiConsumer** | `T, U` | `void` | `accept()` |
| **UnaryOperator** | `T` | `T` | `apply()` |
| **BinaryOperator** | `T, T` | `T` | `apply()` |

> *`T` = input type, `U` = second input type, `R` = return type.*

---

### 2.1 Predicate – Check a Condition *(Most important)*

**Definition:** `Predicate` takes one input and returns a `boolean` value (`true` or `false`). It is mainly used for filtering and condition checking.

* **Syntax:** `Predicate<T>`
* **Abstract method:** `boolean test(T t);`

#### Real-world example
Suppose we have a list of employees and want to find employees whose salary is greater than ₹50,000.

```java
import java.util.function.Predicate;

public class PredicateDemo {

    public static void main(String[] args) {

        // Check whether employee salary is greater than 50000
        Predicate<Integer> highSalary =
                salary -> salary > 50000;

        // test() executes the condition
        System.out.println(highSalary.test(60000));
        System.out.println(highSalary.test(30000));
    }
}
```

**Output:**
```text
true
false
```

#### Predicate with String

```java
Predicate<String> isJava =
        language -> language.equals("Java");

System.out.println(isJava.test("Java"));   // true
System.out.println(isJava.test("Python")); // false
```

#### Predicate Chaining
`Predicate` provides three important default methods:

| Method | Meaning |
| :--- | :--- |
| `and()` | Both conditions must be true |
| `or()` | At least one condition must be true |
| `negate()` | Reverse the result |

**Example:**

```java
Predicate<Integer> greaterThan10 =
        number -> number > 10;

Predicate<Integer> lessThan100 =
        number -> number < 100;

// Both conditions must be true
Predicate<Integer> between10And100 =
        greaterThan10.and(lessThan100);

System.out.println(between10And100.test(50));  // true
System.out.println(between10And100.test(150)); // false

// At least one condition must be true
Predicate<Integer> condition =
        greaterThan10.or(lessThan100);

System.out.println(condition.test(5)); // true

// Reverse the condition
System.out.println(greaterThan10.negate().test(5)); // true
```

> **Interview point:** `Predicate` is commonly used with `Stream.filter()`.

```java
List<Integer> numbers = List.of(10, 15, 20, 25, 30);

numbers.stream()
       .filter(number -> number > 20)
       .forEach(System.out::println);
```

---

### 2.2 Function – Transform One Value into Another

**Definition:** `Function` takes one input and returns another value. The input and output types can be different.

* **Syntax:** `Function<T, R>`
* **Abstract method:** `R apply(T t);`

#### Real-world example
Convert an employee's name into uppercase.

```java
import java.util.function.Function;

public class FunctionDemo {

    public static void main(String[] args) {

        // Input: String
        // Output: String
        Function<String, String> convertToUppercase =
                name -> name.toUpperCase();

        System.out.println(
                convertToUppercase.apply("praveen")
        );

        // Input: String
        // Output: Integer
        Function<String, Integer> getNameLength =
                name -> name.length();

        System.out.println(
                getNameLength.apply("Praveen")
        );

        // Input: Integer
        // Output: String
        Function<Integer, String> convertToString =
                number -> "Employee ID: " + number;

        System.out.println(
                convertToString.apply(101)
        );
    }
}
```

**Output:**
```text
PRAVEEN
7
Employee ID: 101
```

#### Function Chaining
`Function` provides two important methods:
* `andThen()` – Execute the current function first, then the next function.
* `compose()` – Execute the supplied function first, then the current function.

**Example:**

```java
Function<Integer, Integer> multiplyBy2 =
        number -> number * 2;

Function<Integer, Integer> add10 =
        number -> number + 10;

// First multiply by 2, then add 10
Function<Integer, Integer> result1 =
        multiplyBy2.andThen(add10);

System.out.println(result1.apply(5)); // 20

// First add 10, then multiply by 2
Function<Integer, Integer> result2 =
        multiplyBy2.compose(add10);

System.out.println(result2.apply(5)); // 30
```

> **Interview point:** `Function` is commonly used with `Stream.map()`.

```java
List<String> names = List.of("java", "spring", "hibernate");

names.stream()
     .map(name -> name.toUpperCase())
     .forEach(System.out::println);
```

---

### 2.3 Consumer – Consume Data Without Returning Anything

**Definition:** `Consumer` accepts one input but does not return anything. It is mainly used for printing, logging, sending notifications, and processing data.

* **Syntax:** `Consumer<T>`
* **Abstract method:** `void accept(T t);`

#### Example

```java
import java.util.function.Consumer;

public class ConsumerDemo {

    public static void main(String[] args) {

        // Consumer accepts a String and prints it
        Consumer<String> printMessage =
                message -> System.out.println(message);

        printMessage.accept("Welcome to Java 8");

        // Consumer for employee details
        Consumer<String> employeeLogger =
                name -> System.out.println(
                        "Employee Name: " + name
                );

        employeeLogger.accept("Praveen");
    }
}
```

**Output:**
```text
Welcome to Java 8
Employee Name: Praveen
```

#### Consumer Chaining
`Consumer` provides `andThen()`.

```java
Consumer<String> printName =
        name -> System.out.println("Name: " + name);

Consumer<String> printLength =
        name -> System.out.println("Length: " + name.length());

Consumer<String> combinedConsumer =
        printName.andThen(printLength);

combinedConsumer.accept("Praveen");
```

**Output:**
```text
Name: Praveen
Length: 7
```

> **Interview point:** `Consumer` is commonly used with `Stream.forEach()`.

---

### 2.4 Supplier – Supply Data Without Taking Input

**Definition:** `Supplier` does not accept any input but returns a value. It is useful for generating values, creating objects, and providing default values.

* **Syntax:** `Supplier<T>`
* **Abstract method:** `T get();`

#### Example

```java
import java.util.function.Supplier;

public class SupplierDemo {

    public static void main(String[] args) {

        // Supplier does not take input
        // It supplies a String
        Supplier<String> welcomeMessage =
                () -> "Welcome to Java 8";

        System.out.println(welcomeMessage.get());

        // Supplier generates a random number
        Supplier<Double> randomNumber =
                () -> Math.random();

        System.out.println(randomNumber.get());

        // Supplier provides a default value
        Supplier<String> defaultName =
                () -> "Guest";

        System.out.println(defaultName.get());
    }
}
```

**Output:** *(Random number will vary)*
```text
Welcome to Java 8
0.73...
Guest
```

#### Real-world example
`Supplier` can provide an object only when requested (lazy evaluation).

```java
Supplier<StringBuilder> builderSupplier =
        () -> new StringBuilder("Java");

StringBuilder builder = builderSupplier.get();

System.out.println(builder);
```

---

### 2.5 BiPredicate – Check a Condition Using Two Inputs

**Definition:** `BiPredicate` accepts two inputs and returns a `boolean`.

* **Syntax:** `BiPredicate<T, U>`
* **Abstract method:** `boolean test(T t, U u);`

#### Example
Check whether two numbers are equal, or validate credentials.

```java
import java.util.function.BiPredicate;

public class BiPredicateDemo {

    public static void main(String[] args) {

        BiPredicate<Integer, Integer> isEqual =
                (number1, number2) -> number1.equals(number2);

        System.out.println(isEqual.test(10, 10)); // true
        System.out.println(isEqual.test(10, 20)); // false

        // Check username and password
        BiPredicate<String, String> loginCheck =
                (username, password) ->
                        username.equals("admin")
                        && password.equals("1234");

        System.out.println(
                loginCheck.test("admin", "1234")
        );
    }
}
```

**Output:**
```text
true
false
true
```

#### Real-world use cases
* Username and password validation.
* Comparing two objects.
* Checking whether an employee belongs to a department.
* Validating two input values together.

---

### 2.6 BiFunction – Transform Two Inputs into One Output

**Definition:** `BiFunction` accepts two inputs and returns one result.

* **Syntax:** `BiFunction<T, U, R>`
* **Abstract method:** `R apply(T t, U u);`

#### Example
Calculate employee annual salary using monthly salary and bonus.

```java
import java.util.function.BiFunction;

public class BiFunctionDemo {

    public static void main(String[] args) {

        // First input: Monthly salary
        // Second input: Annual bonus
        // Return: Annual salary
        BiFunction<Integer, Integer, Integer> calculateSalary =
                (monthlySalary, bonus) ->
                        (monthlySalary * 12) + bonus;

        int annualSalary =
                calculateSalary.apply(50000, 100000);

        System.out.println(annualSalary);

        // Concatenate two strings
        BiFunction<String, String, String> fullName =
                (firstName, lastName) ->
                        firstName + " " + lastName;

        System.out.println(
                fullName.apply("Praveen", "Kushwaha")
        );
    }
}
```

**Output:**
```text
700000
Praveen Kushwaha
```

> **Interview point:** `BiFunction` is useful when two inputs are required to calculate or transform one result.

---

### 2.7 BiConsumer – Consume Two Inputs Without Returning Anything

**Definition:** `BiConsumer` accepts two inputs and returns nothing.

* **Syntax:** `BiConsumer<T, U>`
* **Abstract method:** `void accept(T t, U u);`

#### Example
Print employee name and salary.

```java
import java.util.function.BiConsumer;

public class BiConsumerDemo {

    public static void main(String[] args) {

        BiConsumer<String, Integer> employeeDetails =
                (name, salary) -> {
                    System.out.println("Name: " + name);
                    System.out.println("Salary: " + salary);
                };

        employeeDetails.accept("Praveen", 50000);

        // Print key-value pair
        BiConsumer<String, String> printKeyValue =
                (key, value) ->
                        System.out.println(key + " = " + value);

        printKeyValue.accept("City", "Bengaluru");
    }
}
```

**Output:**
```text
Name: Praveen
Salary: 50000
City = Bengaluru
```

#### Consumer vs BiConsumer

| Consumer | BiConsumer |
| :--- | :--- |
| Takes one input | Takes two inputs |
| Returns nothing | Returns nothing |
| `accept(T)` | `accept(T, U)` |

---

### 2.8 UnaryOperator – Takes One Input and Returns the Same Type

**Definition:** `UnaryOperator` is a special type of `Function` where input and output types are the same. It extends `Function<T, T>`.

* **Syntax:** `UnaryOperator<T>`
* **Abstract method:** `T apply(T t);`

#### Example
Increase a number by 10.

```java
import java.util.function.UnaryOperator;

public class UnaryOperatorDemo {

    public static void main(String[] args) {

        UnaryOperator<Integer> increase =
                number -> number + 10;

        System.out.println(increase.apply(10)); // 20

        // Convert String into uppercase
        UnaryOperator<String> uppercase =
                text -> text.toUpperCase();

        System.out.println(
                uppercase.apply("java")
        );

        // Square a number
        UnaryOperator<Integer> square =
                number -> number * number;

        System.out.println(square.apply(5));
    }
}
```

**Output:**
```text
20
JAVA
25
```

#### Function vs UnaryOperator

```java
// Input String, Output Integer
Function<String, Integer> getLength =
        text -> text.length();

// Input Integer, Output Integer
UnaryOperator<Integer> square =
        number -> number * number;
```

> Use `UnaryOperator` when input and output have the same type.

---

### 2.9 BinaryOperator – Takes Two Inputs of the Same Type and Returns the Same Type

**Definition:** `BinaryOperator` is a special type of `BiFunction` where both input types and the return type are the same. It extends `BiFunction<T, T, T>`.

* **Syntax:** `BinaryOperator<T>`
* **Abstract method:** `T apply(T t1, T t2);`

#### Example
Add two numbers.

```java
import java.util.function.BinaryOperator;

public class BinaryOperatorDemo {

    public static void main(String[] args) {

        BinaryOperator<Integer> addition =
                (number1, number2) -> number1 + number2;

        System.out.println(addition.apply(10, 20));

        // Find maximum number
        BinaryOperator<Integer> maximum =
                (number1, number2) ->
                        number1 > number2 ? number1 : number2;

        System.out.println(maximum.apply(50, 30));

        // Combine two strings
        BinaryOperator<String> concatenate =
                (first, second) -> first + " " + second;

        System.out.println(
                concatenate.apply("Hello", "Java")
        );
    }
}
```

**Output:**
```text
30
50
Hello Java
```

#### BinaryOperator vs BiFunction

```java
// Two Integer inputs, String output
BiFunction<Integer, Integer, String> result =
        (a, b) -> "Sum: " + (a + b);

// Two Integer inputs, Integer output
BinaryOperator<Integer> sum =
        (a, b) -> a + b;
```

> Use `BinaryOperator` when both inputs and the output have the same type.

---

## 3. Most Important Comparison – All 9 Interfaces

This table is extremely important for Java interviews.

| Interface | Number of inputs | Return value | Abstract method |
| :--- | :--- | :--- | :--- |
| **Predicate** | 1 | Boolean | `test()` |
| **Function** | 1 | Any type | `apply()` |
| **Consumer** | 1 | Nothing | `accept()` |
| **Supplier** | 0 | Any type | `get()` |
| **BiPredicate** | 2 | Boolean | `test()` |
| **BiFunction** | 2 | Any type | `apply()` |
| **BiConsumer** | 2 | Nothing | `accept()` |
| **UnaryOperator** | 1 | Same type | `apply()` |
| **BinaryOperator** | 2 | Same type | `apply()` |

---

## 4. Understanding the Difference Through One Example

Suppose you are developing an **Employee Management System**. You have an employee:

```java
String name = "Praveen";
int salary = 50000;
```

How would you use the nine interfaces?

| Interface | Employee example |
| :--- | :--- |
| **Predicate** | Check whether salary is greater than `40000` |
| **Function** | Convert employee name to uppercase |
| **Consumer** | Print employee details |
| **Supplier** | Generate a default employee name |
| **BiPredicate** | Check username and password |
| **BiFunction** | Calculate annual salary using monthly salary and bonus |
| **BiConsumer** | Print employee name and salary |
| **UnaryOperator** | Increase salary by 10% |
| **BinaryOperator** | Add two salary amounts |

---

## 5. Important Relationship Between Functional Interfaces

Understand this hierarchy because interviewers frequently ask about it.

### Functional Interface Hierarchy

```text
Function<T, R>          (One input → One output)
   └── UnaryOperator<T> (Extends Function<T, T>)

BiFunction<T, U, R>     (Two inputs → One output)
   └── BinaryOperator<T>(Extends BiFunction<T, T, T>)
```

**Other specializations:**
* `BiPredicate<T, U>` – two inputs, boolean result.
* `BiConsumer<T, U>` – two inputs, no result.

> **Important:** `UnaryOperator` extends `Function`, and `BinaryOperator` extends `BiFunction`. The remaining interfaces are separate functional interfaces.

---

## 6. How Functional Interfaces Work with Stream API

This is where functional interfaces become extremely useful in real Java applications. Consider an employee list:

```java
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
    }
}
```

**Output:**
```text
Updated Salary: 55000
Updated Salary: 66000
Updated Salary: 88000
```

### Understand the flow

1. **List of Salaries:** `20k, 30k, 50k, 60k, 80k`
2. **Predicate – `filter()`:** Keep salaries greater than `40k`
3. **Function – `map()`:** Increase each salary by 10%
4. **Consumer – `forEach()`:** Print the final salaries

**Remember these three relationships:**
* `filter()` → `Predicate`
* `map()` → `Function`
* `forEach()` → `Consumer`

---

## 7. Important Interview Questions and Answers

**Q1. What is a Functional Interface?**  
An interface containing exactly one abstract method is called a Functional Interface. It can be implemented using lambda expressions and method references.

**Q2. What is SAM?**  
SAM stands for *Single Abstract Method*. It means an interface has exactly one abstract method.

**Q3. What is the purpose of `@FunctionalInterface`?**  
It instructs the compiler to verify that the interface contains exactly one abstract method. It helps prevent accidental addition of another abstract method.

**Q4. Can a Functional Interface contain default methods?**  
Yes. It can contain multiple `default` and `static` methods because they do not add another abstract method.

**Q5. What is the difference between `Predicate` and `Function`?**  
`Predicate` returns a `boolean` and is mainly used for checking conditions. `Function` transforms an input into an output.

**Q6. What is the difference between `Consumer` and `Supplier`?**  
`Consumer` accepts input but returns nothing. `Supplier` accepts no input but supplies a result.

**Q7. What is the difference between `Function` and `UnaryOperator`?**  
`Function` can accept one type and return another type. `UnaryOperator` always uses the same type for input and output.

**Q8. What is the difference between `BiFunction` and `BinaryOperator`?**  
`BiFunction` can accept two different input types and return another type. `BinaryOperator` takes two inputs of the same type and returns that same type.

**Q9. What is the difference between `Predicate` and `BiPredicate`?**  
`Predicate` accepts one input; `BiPredicate` accepts two inputs. Both return `boolean`.

**Q10. Which package contains built-in functional interfaces?**  
`java.util.function`

**Q11. Can we use lambda expressions without functional interfaces?**  
A lambda expression needs a functional interface target type (or another compatible functional target type, such as a method parameter). A lambda cannot be assigned to an ordinary class or interface with multiple abstract methods.

**Q12. What is the difference between `andThen()` and `compose()`?**  
* `andThen()` executes the current function first.
* `compose()` executes the supplied function first.

---

## 8. Practice Exercises

Try these exercises yourself before expanding the solutions.

1. **Create a `Predicate` to check whether a number is even.**
   <details>
   <summary>Show solution</summary>

   ```java
   Predicate<Integer> isEven = num -> num % 2 == 0;
   ```
   </details>

2. **Create a `Function` to calculate the length of a `String`.**
   <details>
   <summary>Show solution</summary>

   ```java
   Function<String, Integer> strLength = str -> str.length();
   ```
   </details>

3. **Create a `Consumer` to print a name.**
   <details>
   <summary>Show solution</summary>

   ```java
   Consumer<String> printName = name -> System.out.println(name);
   ```
   </details>

4. **Create a `Supplier` to generate a random integer.**
   <details>
   <summary>Show solution</summary>

   ```java
   Supplier<Integer> randomInt = () -> new java.util.Random().nextInt();
   ```
   </details>

5. **Create a `BiPredicate` to check whether the first number is greater than the second.**
   <details>
   <summary>Show solution</summary>

   ```java
   BiPredicate<Integer, Integer> isGreater = (a, b) -> a > b;
   ```
   </details>

6. **Create a `BiFunction` to multiply two numbers.**
   <details>
   <summary>Show solution</summary>

   ```java
   BiFunction<Integer, Integer, Integer> multiply = (a, b) -> a * b;
   ```
   </details>

7. **Create a `BiConsumer` to print a name and age.**
   <details>
   <summary>Show solution</summary>

   ```java
   BiConsumer<String, Integer> printDetails = (name, age) -> System.out.println(name + " is " + age);
   ```
   </details>

8. **Create a `UnaryOperator` to double a number.**
   <details>
   <summary>Show solution</summary>

   ```java
   UnaryOperator<Integer> doubleNum = num -> num * 2;
   ```
   </details>

9. **Create a `BinaryOperator` to find the maximum of two numbers.**
   <details>
   <summary>Show solution</summary>

   ```java
   BinaryOperator<Integer> maxNum = (a, b) -> a > b ? a : b;
   ```
   </details>

---

## 9. Recommended Folder Structure

Your current structure is good. I recommend adding a `README.md` file for revision.

```text
p02_functional_interfaces/
│
├── PredicateDemo.java
├── FunctionDemo.java
├── ConsumerDemo.java
├── SupplierDemo.java
│
├── BiPredicateDemo.java
├── BiFunctionDemo.java
├── BiConsumerDemo.java
│
├── UnaryOperatorDemo.java
├── BinaryOperatorDemo.java
│
├── FunctionalInterfaceStreamDemo.java
│
└── README.md
```

### Recommended Learning Sequence

| Order | Topic | Priority |
| :--- | :--- | :--- |
| 1 | Predicate | Very High |
| 2 | Function | Very High |
| 3 | Consumer | Very High |
| 4 | Supplier | High |
| 5 | BiPredicate | Medium |
| 6 | BiFunction | High |
| 7 | BiConsumer | Medium |
| 8 | UnaryOperator | High |
| 9 | BinaryOperator | High |
| 10 | Stream API integration | Very High |

---

## 10. Final Revision – Remember This

### One-line revision trick
* **Predicate:** Check something.
* **Function:** Convert something.
* **Consumer:** Consume something.
* **Supplier:** Supply something.
* **BiPredicate:** Check two things.
* **BiFunction:** Convert two inputs.
* **BiConsumer:** Consume two inputs.
* **UnaryOperator:** Modify one value, same type.
* **BinaryOperator:** Combine two values, same type.

### Most important for interviews
1. Understand lambda expressions.
2. Understand all four basic interfaces.
3. Understand `BiFunction` and `BiPredicate`.
4. Understand `Function` vs `UnaryOperator`.
5. Understand `BiFunction` vs `BinaryOperator`.
6. Understand how `Predicate`, `Function`, and `Consumer` work with Streams.

> **Recommendation:** After completing these nine interfaces, study **Method References** and then **Stream API**. These topics build directly on functional interfaces and are frequently tested in Java backend interviews.