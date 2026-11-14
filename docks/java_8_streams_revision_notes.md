# Java 8 Streams API — Comprehensive Revision Notes

The **Java 8 Stream API** (`java.util.stream`) provides a functional, declarative way to process sequences of elements (such as collections, arrays, or I/O channels). 

### Key Characteristics of Streams:
* **Not a Data Structure:** Streams do not store data; they convey elements from a source through a pipeline of computational operations.
* **Functional in Nature:** Operations produce a result without modifying the underlying source.
* **Laziness-Seeking:** Intermediate operations are lazy—they are not executed until a terminal operation is invoked.
* **Consumable:** A stream can only be traversed once. Attempting to reuse a consumed stream throws an `IllegalStateException`.

---

## 1. Stream Creation (`StreamCreation.java`)

Streams can be instantiated from several different data sources:

### 1.1 From Collections (`List`, `Set`, etc.)
Every `Collection` interface has default methods to generate streams:
* **`collection.stream()`**: Creates a **sequential stream** that processes elements one by one in a single thread.
* **`collection.parallelStream()`**: Creates a **parallel stream** that splits elements across multiple threads (using the common `ForkJoinPool`) for concurrent execution.

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Sequential
Stream<String> seqStream = names.stream();

// Parallel
Stream<String> parStream = names.parallelStream();
```

### 1.2 From Arrays
Use the `Arrays.stream()` utility method to convert an array into a stream.
```java
String[] nameArray = {"Alice", "Bob", "Charlie"};
Stream<String> arrayStream = Arrays.stream(nameArray);
```

### 1.3 From Individual Values (`Stream.of()`)
Directly pass a comma-separated list of elements (varargs) to create a stream.
```java
Stream<String> valStream = Stream.of("Alice", "Bob", "Charlie");
```

### 1.4 Primitive Streams (`IntStream`, `LongStream`, `DoubleStream`)
Specialized streams to avoid the performance overhead of auto-boxing/unboxing primitives.
* **`IntStream.range(start, end)`**: Generates numbers from `start` to `end - 1` (**upper bound is exclusive**).
* **`IntStream.rangeClosed(start, end)`**: Generates numbers from `start` to `end` (**upper bound is inclusive**).

```java
IntStream.range(1, 6);       // Emits: 1, 2, 3, 4, 5
IntStream.rangeClosed(1, 5); // Emits: 1, 2, 3, 4, 5
```

### 1.5 Infinite Streams
Infinite streams generate elements on the fly without a fixed size. **You must use a short-circuiting operation like `.limit(n)`** to prevent an infinite loop.
* **`Stream.iterate(seed, UnaryOperator)`**: Starts with an initial `seed` value and iteratively applies a function to the previous element.
  ```java
  Stream.iterate(0, n -> n + 2).limit(5); // Emits: 0, 2, 4, 6, 8
  ```
* **`Stream.generate(Supplier)`**: Dynamically generates values using a `Supplier` functional interface (stateless).
  ```java
  Stream.generate(Math::random).limit(5); // Emits 5 random doubles
  ```

---

## 2. Intermediate Operations (`AllStreamlined.java`)

Intermediate operations transform a stream into another stream. They are **lazy** and can be chained together to form a pipeline.

| Operation | Functional Interface | Purpose |
| :--- | :--- | :--- |
| **`filter()`** | `Predicate<T>` | Retains only elements that match the given boolean condition. |
| **`map()`** | `Function<T, R>` | Transforms each element into another object/type (1-to-1 mapping). |
| **`flatMap()`** | `Function<T, Stream<R>>` | Transforms each element into a stream and flattens all resulting streams into a single stream (1-to-Many mapping). |
| **`sorted()`** | `Comparator<T>` (Optional) | Sorts elements in natural order or according to a custom `Comparator`. |
| **`distinct()`** | *None* | Removes duplicate elements using `Object.equals()` and `hashCode()`. |
| **`limit(long n)`** | *None* | Truncates the stream to contain no more than `n` elements (short-circuiting). |
| **`skip(long n)`** | *None* | Discards the first `n` elements of the stream. |
| **`peek()`** | `Consumer<T>` | Performs an action on each element as it passes through the pipeline without modifying the stream (primarily used for debugging). |

### Key Distinctions & Patterns:
* **`map()` vs. `flatMap()`**:
  * If you have `List<List<Integer>>` and call `.map(List::stream)`, you get `Stream<Stream<Integer>>`.
  * Calling `.flatMap(List::stream)` unwraps the inner streams and gives you a flat `Stream<Integer>`.
  * Useful for splitting sentences into words: `.flatMap(s -> Arrays.stream(s.split(" ")))`.
* **Pagination Pattern**:
  Combining `skip()` and `limit()` allows easy pagination:
  ```java
  numbers.stream().skip(2).limit(3); // Skips first 2, takes next 3
  ```
* **Syntax Note from Code**:
  When multiplying in `.map()`, ensure the `*` operator is present: `.map(number -> number * number)` or `.map(number -> number * 2)`.

---

## 3. Terminal Operations (`TerminalOperationsRevision.java`)

Terminal operations trigger the execution of the entire lazy stream pipeline and produce a non-stream result (a value, collection, boolean, or side effect).

### 3.1 Iteration
* **`forEach(Consumer<T>)`**: Performs an action for each element in the stream.

### 3.2 Reduction & Aggregation
* **`reduce(identity, BinaryOperator<T>)`**: Combines stream elements repeatedly into a single summary result (e.g., sum, product).
  * *With identity*: `numbers.stream().reduce(0, Integer::sum)` returns `T` (e.g., `int`).
  * *Without identity*: `numbers.stream().reduce(Integer::sum)` returns `Optional<T>` because the stream might be empty.
* **`count()`**: Returns the total number of elements in the stream as a `long`.
* **`min(Comparator<T>)` & `max(Comparator<T>)`**: Returns the minimum or maximum element wrapped in an `Optional<T>`.

### 3.3 Short-Circuiting Match Operations
These operations accept a `Predicate<T>`, return a `boolean`, and stop processing as soon as the outcome is determined:
* **`anyMatch(Predicate<T>)`**: Returns `true` if **at least one** element matches the predicate.
* **`allMatch(Predicate<T>)`**: Returns `true` if **every** element matches the predicate.
* **`noneMatch(Predicate<T>)`**: Returns `true` if **no** elements match the predicate.

---

## 4. Collectors (`CollectorsRevision.java`)

The `collect(Collector)` terminal operation accumulates stream elements into data structures or summary values using utility methods from `java.util.stream.Collectors`.

### 4.1 Basic Collection Collectors
* **`Collectors.toList()`**: Accumulates elements into a mutable `List` (Note: Java 16+ also offers `.toList()` directly on `Stream` which returns an unmodifiable list).
* **`Collectors.toSet()`**: Accumulates unique elements into a `Set` (automatically removes duplicates).
* **`Collectors.toMap(keyMapper, valueMapper, [mergeFunction])`**: Converts elements into a `Map`.
  * *Duplicate Keys*: If keys are not unique, `toMap()` throws an `IllegalStateException`. You must either call `.distinct()` beforehand or provide a **merge function** to resolve collisions:
    ```java
    Collectors.toMap(
        name -> name,
        String::length,
        (oldValue, newValue) -> oldValue // Keeps the existing value on collision
    )
    ```

### 4.2 Grouping & Partitioning
* **`Collectors.groupingBy(classifier)`**: Groups elements by a property/condition into a `Map<K, List<T>>`.
  * Example: `Collectors.groupingBy(String::length)` groups strings by their character count.
* **`Collectors.partitioningBy(Predicate<T>)`**: A specialized form of grouping that splits elements into exactly two groups (`true` and `false`), returning a `Map<Boolean, List<T>>`.

### 4.3 String Joining
* **`Collectors.joining()`**: Concatenates `CharSequence` elements into a single `String`.
  * `Collectors.joining()` $\rightarrow$ `"PraveenAmitRahul"`
  * `Collectors.joining(", ")` $\rightarrow$ `"Praveen, Amit, Rahul"` (with delimiter)
  * `Collectors.joining(", ", "[", "]")` $\rightarrow$ `"[Praveen, Amit, Rahul]"` (with delimiter, prefix, and suffix)

### 4.4 Statistical & Summary Collectors
* **`Collectors.counting()`**: Counts the number of elements (returns `Long`).
* **`Collectors.summingInt(ToIntFunction)`**: Sums an integer property of the elements (returns `Integer`).
* **`Collectors.averagingInt(ToIntFunction)`**: Calculates the arithmetic mean of an integer property (returns `Double`).
* **`Collectors.maxBy(Comparator)` & `Collectors.minBy(Comparator)`**: Finds the max/min element according to a comparator (returns `Optional<T>`).

### 4.5 Downstream Collectors (Multi-level Reduction)
You can pass a second collector (a **downstream collector**) into `groupingBy()` or `partitioningBy()` to perform aggregations on each grouped bucket rather than just collecting them into a `List`:

```java
// Count occurrences of each name -> Map<String, Long>
names.stream()
     .collect(Collectors.groupingBy(name -> name, Collectors.counting()));

// Sum numbers grouped by Even (true) vs Odd (false) -> Map<Boolean, Integer>
numbers.stream()
       .collect(Collectors.partitioningBy(
           n -> n % 2 == 0,
           Collectors.summingInt(Integer::intValue)
       ));
```

---

## Quick Reference Summary

1. **Source** $\rightarrow$ `list.stream()`, `Arrays.stream()`, `Stream.of()`, `IntStream.range()`, `Stream.iterate()`
2. **Intermediate (Lazy)** $\rightarrow$ `filter()`, `map()`, `flatMap()`, `distinct()`, `sorted()`, `peek()`, `skip()`, `limit()`
3. **Terminal (Eager)** $\rightarrow$ `forEach()`, `collect()`, `reduce()`, `count()`, `min()`, `max()`, `anyMatch()`, `allMatch()`, `noneMatch()`