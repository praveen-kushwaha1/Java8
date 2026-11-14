# Java 8 Lambda Expressions – Quick Revision Notes

> **Key Takeaway:** Lambda expressions are used to implement **Functional Interfaces** in a concise, readable way.

---

## 1. What is a Lambda Expression?
A **Lambda Expression** is a short and simple way to write code that implements a **Functional Interface**.

* **In simple words:** `Lambda = A shorter way to write an implementation of an interface method.`
* **Real-life Analogy:** Imagine an interface called `Greeting` with a single method `sayHello()`. Before Java 8, implementing this required boilerplate code (Anonymous Inner Classes). Java 8 lets you write just the core logic.

---

## 2. Before Java 8 vs. After Java 8

### Before Java 8 (Anonymous Inner Class)
```java
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        Greeting greeting = new Greeting() {
            @Override
            public void sayHello() {
                System.out.println("Hello Java");
            }
        };

        greeting.sayHello();
    }
}
```
**Output:**
```text
Hello Java
```

### After Java 8 (Lambda Expression)
```java
interface Greeting {
    void sayHello();
}

public class Main {
    public static void main(String[] args) {
        Greeting greeting = () -> {
            System.out.println("Hello Java");
        };

        greeting.sayHello();
    }
}
```
**Output:**
```text
Hello Java
```
> *Both programs produce the exact same output, but Lambda drastically reduces unnecessary boilerplate code.*

---

## 3. Lambda Syntax
```java
(parameters) -> { logic }
```

**Example:**
```java
(int a, int b) -> {
    return a + b;
}
```

### Breakdown:
* `(int a, int b)` $\rightarrow$ **Input parameters**
* `->` $\rightarrow$ **Lambda operator** (separates input parameters from the body/logic)
* `{ return a + b; }` $\rightarrow$ **Body / logic**

---

## 4. Lambda Examples by Parameter Type

### Example 1: No Parameter
```java
Runnable r = () -> System.out.println("Hello");
r.run();
```

### Example 2: One Parameter
*(Parentheses are optional for a single parameter without an explicit type)*
```java
Consumer<String> c = name -> System.out.println(name);
c.accept("Praveen");
```

### Example 3: Two Parameters
```java
BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
System.out.println(sum.apply(10, 20)); // Output: 30
```

### Example 4: With Return Value
*(The `return` keyword and curly braces `{}` are optional for single-line expressions)*
```java
Function<Integer, Integer> square = n -> n * n;
System.out.println(square.apply(5)); // Output: 25
```

---

## 5. Important Concept: Functional Interface
A **Functional Interface** is an interface that contains **exactly one abstract method** (SAM - Single Abstract Method).

### Example:
```java
@FunctionalInterface
interface Calculator {
    int add(int a, int b);
}

public class Test {
    public static void main(String[] args) {
        Calculator calculator = (a, b) -> a + b;
        System.out.println(calculator.add(10, 20)); // Output: 30
    }
}
```

### Why does this work?
1. `Calculator` has only one abstract method: `add()`.
2. The Lambda expression provides the implementation for `add()`.
3. Calling `calculator.add(10, 20)` executes the lambda's logic.

---

## 6. Common Built-in Functional Interfaces (`java.util.function`)
These four core interfaces are heavily used in the **Java 8 Stream API**:

| Interface | Purpose | Abstract Method | Example |
| :--- | :--- | :--- | :--- |
| **`Predicate<T>`** | Checks a condition (returns `boolean`) | `test(T t)` | `n -> n > 10` |
| **`Function<T, R>`** | Converts/transforms a value (`T` to `R`) | `apply(T t)` | `n -> n * 2` |
| **`Consumer<T>`** | Consumes a value (no return / `void`) | `accept(T t)` | `n -> System.out.println(n)` |
| **`Supplier<T>`** | Supplies/generates a value (no input) | `get()` | `() -> "Hello"` |

---

## 7. Top Interview Questions for Practice
1. What is a Lambda Expression in Java 8?
2. Why was Lambda introduced in Java 8?
3. What is a Functional Interface?
4. Can a Functional Interface have multiple methods? *(Hint: Only one abstract method, though `default` and `static` methods are allowed).*
5. What is the difference between a Lambda Expression and an Anonymous Inner Class?
6. What is the purpose of the `->` operator?
7. Can a Lambda access local variables?
8. What does *effectively final* mean in the context of Lambdas?
9. Can we use a Lambda Expression with abstract classes? *(Hint: No, only with Functional Interfaces).*
10. What are `Predicate`, `Function`, `Consumer`, and `Supplier`?