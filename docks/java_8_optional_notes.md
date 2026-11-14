# Java 8 `Optional` – Complete Beginner-Friendly Notes

`Optional` is a container class introduced in Java 8 (`java.util.Optional`). Its main purpose is to avoid unnecessary `NullPointerException`s by explicitly representing a value that may or may not be present.

---

## 1. Why Do We Need `Optional`?

Suppose you have a method that returns a username:

```java
public String getUsername() {
    return null;
}
```

If you call a method on the returned value without checking for `null`:

```java
String name = getUsername();
System.out.println(name.toUpperCase()); // Throws NullPointerException
```

**Solution: Use `Optional`**

```java
Optional<String> name = Optional.empty();
```

Now Java explicitly tells us that the value might be absent. Think of `Optional` as a box:

| State | Content | Code Representation |
| :--- | :--- | :--- |
| **Value Present** | `"Praveen"` | `Optional.of("Praveen")` |
| **Value Absent** | Empty | `Optional.empty()` |

---

## 2. Creating `Optional` Objects (`OptionalCreation.java`)

There are three primary ways to create `Optional` objects:

### A. `Optional.of()`
Use when you are **100% sure** the value is not `null`.

```java
Optional<String> name = Optional.of("Praveen");
System.out.println(name); // Output: Optional[Praveen]

// Passing null throws an immediate exception:
Optional<String> nullName = Optional.of(null); // Output: NullPointerException
```

### B. `Optional.ofNullable()`
Use when the value **may be `null`**.

```java
String name1 = null;
Optional<String> result1 = Optional.ofNullable(name1);
System.out.println(result1); // Output: Optional.empty

String name2 = "Praveen";
Optional<String> result2 = Optional.ofNullable(name2);
System.out.println(result2); // Output: Optional[Praveen]
```

### C. `Optional.empty()`
Use when you explicitly want to create an empty `Optional`.

```java
Optional<String> result = Optional.empty();
System.out.println(result); // Output: Optional.empty
```

### Creation Methods Summary

| Method | Purpose |
| :--- | :--- |
| `of()` | Value must **not** be `null` |
| `ofNullable()` | Value **may** be `null` |
| `empty()` | Creates an empty `Optional` |

---

## 3. Checking Value Presence (`IsPresentDemo.java`)

`isPresent()` checks whether a value exists inside the `Optional`.
* `true` $\rightarrow$ Value exists.
* `false` $\rightarrow$ Value does not exist.

```java
import java.util.Optional;

public class IsPresentDemo {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Praveen");
        System.out.println(name.isPresent()); // Output: true

        Optional<String> emptyName = Optional.empty();
        System.out.println(emptyName.isPresent()); // Output: false
    }
}
```

**Real-world usage with `get()`:**
```java
Optional<String> username = Optional.ofNullable("Praveen");

if (username.isPresent()) {
    System.out.println(username.get()); // Output: Praveen
}
```
> **Note:** `get()` throws `NoSuchElementException` if the `Optional` is empty. Prefer safer alternatives like `ifPresent()`, `orElse()`, or `orElseGet()`.

---

## 4. Conditional Execution (`IfPresentDemo.java`)

`ifPresent()` executes a Lambda Expression only when a value is available, eliminating the need for an explicit `if` check.

* **Value available** $\rightarrow$ Execute code.
* **Value missing** $\rightarrow$ Do nothing.

```java
import java.util.Optional;

public class IfPresentDemo {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Praveen");
        // Executes when value is present
        name.ifPresent(value -> System.out.println(value)); // Output: Praveen

        Optional<String> emptyName = Optional.empty();
        // Nothing executes because Optional is empty
        emptyName.ifPresent(value -> System.out.println(value));
    }
}
```

---

## 5. Default Values with `orElse()` (`OrElseDemo.java`)

`orElse()` returns the actual value if present; otherwise, it returns a specified default value.

```java
import java.util.Optional;

public class OrElseDemo {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Praveen");
        String result = name.orElse("Unknown");
        System.out.println(result); // Output: Praveen

        Optional<String> emptyName = Optional.empty();
        String result2 = emptyName.orElse("Unknown");
        System.out.println(result2); // Output: Unknown
    }
}
```

**Real-world example:**
```java
Optional<String> city = Optional.empty();
String userCity = city.orElse("Delhi");
System.out.println(userCity); // Output: Delhi
```

---

## 6. Lazy Default Values with `orElseGet()` (`OrElseGetDemo.java`)

`orElseGet()` is similar to `orElse()`, but it accepts a `Supplier` (Lambda Expression).

```java
import java.util.Optional;

public class OrElseGetDemo {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Praveen");
        String result = name.orElseGet(() -> "Unknown");
        System.out.println(result); // Output: Praveen

        Optional<String> emptyName = Optional.empty();
        String result2 = emptyName.orElseGet(() -> "Unknown");
        System.out.println(result2); // Output: Unknown
    }
}
```

### Crucial Interview Difference: `orElse()` vs `orElseGet()`

Consider the following helper method:
```java
public static String getDefaultName() {
    System.out.println("Generating default name");
    return "Unknown";
}
```

**1. Using `orElse()` (Eager Evaluation):**
```java
Optional<String> name = Optional.of("Praveen");
String result = name.orElse(getDefaultName());
System.out.println(result);
```
*Output:*
```text
Generating default name
Praveen
```
*Why?* `orElse()` evaluates its argument **even when a value is already present**.

**2. Using `orElseGet()` (Lazy Evaluation):**
```java
Optional<String> name = Optional.of("Praveen");
String result = name.orElseGet(() -> getDefaultName());
System.out.println(result);
```
*Output:*
```text
Praveen
```
*Why?* `orElseGet()` invokes the `Supplier` **only when the `Optional` is empty**.

| Feature | `orElse()` | `orElseGet()` |
| :--- | :--- | :--- |
| **Argument** | Direct value | `Supplier` |
| **Evaluation** | Always (Eager) | Only when empty (Lazy) |
| **Performance** | May do unnecessary work | Avoids unnecessary computation |
| **Use Case** | Simple, constant defaults | Expensive calculations, DB/API calls |

> **Interview Tip:** Always prefer `orElseGet()` when generating the default value involves database calls, API requests, or expensive operations.

---

## 7. Throwing Exceptions with `orElseThrow()` (`OrElseThrowDemo.java`)

`orElseThrow()` returns the value if present; otherwise, it throws an exception created by the provided `Supplier`.

```java
import java.util.Optional;

public class OrElseThrowDemo {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Praveen");
        String result = name.orElseThrow(() -> new RuntimeException("Name not found"));
        System.out.println(result); // Output: Praveen

        Optional<String> emptyName = Optional.empty();
        // Throws RuntimeException: Name not found
        String result2 = emptyName.orElseThrow(() -> new RuntimeException("Name not found"));
    }
}
```

**Real-world Spring Boot Example:**
```java
public User getUserById(Long id) {
    return userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
}
```
*(In production applications, replace `RuntimeException` with a custom exception like `UserNotFoundException`.)*

---

## 8. Transforming Values with `map()` (`MapOptionalDemo.java`)

`map()` transforms the value inside an `Optional` into another value and wraps the result in a new `Optional`. If the `Optional` is empty, the mapping function does not run and an empty `Optional` is returned.

```java
import java.util.Optional;

public class MapOptionalDemo {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("praveen");
        Optional<String> result = name.map(value -> value.toUpperCase());
        System.out.println(result); // Output: Optional[PRAVEEN]

        Optional<Integer> number = Optional.of(10);
        Optional<Integer> doubled = number.map(n -> n * 2);
        System.out.println(doubled); // Output: Optional[20]
    }
}
```

---

## 9. Avoiding Nested Optionals with `flatMap()` (`FlatMapOptionalDemo.java`)

`flatMap()` is used when your mapping function **already returns an `Optional`**.

**The Problem with `map()`:**
```java
Optional<String> name = Optional.of("Praveen");
Optional<Optional<String>> result = name.map(value -> Optional.of(value.toUpperCase()));
System.out.println(result); // Output: Optional[Optional[PRAVEEN]] (Nested!)
```

**The Solution with `flatMap()`:**
```java
import java.util.Optional;

public class FlatMapOptionalDemo {
    public static void main(String[] args) {
        Optional<String> name = Optional.of("Praveen");
        Optional<String> result = name.flatMap(value -> Optional.of(value.toUpperCase()));
        System.out.println(result); // Output: Optional[PRAVEEN]
    }
}
```

**Real-world Example:**
```java
class User {
    private Address address;

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }
}

// Usage
Optional<User> user = Optional.of(new User());
Optional<Address> address = user.flatMap(User::getAddress);
```

> **Interview Rule:**
> * `map()` $\rightarrow$ Mapping function returns a **normal value** (`T -> R`).
> * `flatMap()` $\rightarrow$ Mapping function returns an **`Optional`** (`T -> Optional<R>`).

---

## 10. Quick Revision Table

| Method | Purpose |
| :--- | :--- |
| `of()` | Create `Optional` with a non-null value |
| `ofNullable()` | Create `Optional` with a nullable value |
| `empty()` | Create an empty `Optional` |
| `isPresent()` | Check if value exists (`boolean`) |
| `get()` | Retrieve value (throws `NoSuchElementException` if empty) |
| `ifPresent()` | Execute action if value exists |
| `orElse()` | Return value or eager default value |
| `orElseGet()` | Return value or lazy default value via `Supplier` |
| `orElseThrow()` | Return value or throw exception if empty |
| `map()` | Transform value and wrap in `Optional` |
| `flatMap()` | Transform value into `Optional` without nesting |

---

## 11. Complete Practice Class (`OptionalCompleteDemo.java`)

```java
import java.util.Optional;

public class OptionalCompleteDemo {

    public static void main(String[] args) {

        // 1. Optional Creation
        Optional<String> name1 = Optional.of("Praveen");
        Optional<String> name2 = Optional.ofNullable(null);
        Optional<String> name3 = Optional.empty();

        System.out.println("Creation: " + name1);
        System.out.println("Nullable: " + name2);
        System.out.println("Empty: " + name3);

        // 2. isPresent()
        System.out.println("\nIs Present: " + name1.isPresent());

        // 3. ifPresent()
        name1.ifPresent(value -> System.out.println("If Present: " + value));

        // 4. orElse()
        String result1 = name2.orElse("Unknown");
        System.out.println("Or Else: " + result1);

        // 5. orElseGet()
        String result2 = name2.orElseGet(() -> "Default User");
        System.out.println("Or Else Get: " + result2);

        // 6. orElseThrow()
        String result3 = name1.orElseThrow(() -> new RuntimeException("Name not found"));
        System.out.println("Or Else Throw: " + result3);

        // 7. map()
        Optional<String> upperName = name1.map(value -> value.toUpperCase());
        System.out.println("Map: " + upperName);

        // 8. flatMap()
        Optional<String> flatName = name1.flatMap(value -> Optional.of(value.toUpperCase()));
        System.out.println("FlatMap: " + flatName);
    }
}
```

---

## 12. Important Java 8 Interview Questions

**Q1. What is `Optional` in Java 8?**
`Optional` is a container object that may or may not contain a non-null value. It helps handle missing values explicitly and reduces `NullPointerException`s.

**Q2. What is the difference between `of()` and `ofNullable()`?**
`of()` throws a `NullPointerException` when passed `null`, whereas `ofNullable()` safely returns an empty `Optional`.

**Q3. What is the difference between `isPresent()` and `ifPresent()`?**
`isPresent()` checks whether a value exists and returns a `boolean`. `ifPresent()` accepts a `Consumer` and executes an action only when the value exists.

**Q4. What is the difference between `orElse()` and `orElseGet()`?**
`orElse()` evaluates its default argument eagerly (always), while `orElseGet()` evaluates its `Supplier` lazily (only when the `Optional` is empty).

**Q5. What is the difference between `map()` and `flatMap()`?**
`map()` transforms a value and wraps the result in an `Optional`. `flatMap()` is used when the transformation function itself returns an `Optional`, preventing nested `Optional<Optional<T>>` wrappers.

**Q6. Can `Optional` contain `null`?**
No. An `Optional` is either present with a non-null value or empty. Passing `null` to `Optional.ofNullable(null)` results in an empty `Optional`.

**Q7. Should we use `Optional` as a class/entity field?**
Generally, no. `Optional` is not `Serializable` and was designed primarily as a method return type to represent the potential absence of a return value.

**Q8. Can we use `Optional` with the Stream API?**
Yes. Many terminal Stream operations return `Optional`, such as `findFirst()`, `findAny()`, `max()`, `min()`, and `reduce()`.

---

## 13. Self-Assessment Quiz

1. **Which method safely creates an `Optional` when the input may be `null`?**
   - [ ] `of()`
   - [x] `ofNullable()`
   - [ ] `get()`
   - [ ] `map()`

2. **Which method executes a `Supplier` only when `Optional` is empty?**
   - [ ] `orElse()`
   - [ ] `ifPresent()`
   - [x] `orElseGet()`
   - [ ] `isPresent()`

3. **Which method avoids nested `Optional` when the mapping function returns an `Optional`?**
   - [ ] `map()`
   - [x] `flatMap()`
   - [ ] `get()`
   - [ ] `of()`

4. **What does `isPresent()` return?**
   - [ ] `String`
   - [ ] `Optional`
   - [x] `boolean`
   - [ ] `void`

---

## Next Steps for Backend Interview Prep
* Master **`orElseGet()`**, **`map()`**, and **`flatMap()`**, as they are heavily used in Spring Data JPA and Stream pipelines.
* Practice chaining these methods with Spring Boot repository calls (e.g., `findById()`), custom exceptions, and DTO conversions.