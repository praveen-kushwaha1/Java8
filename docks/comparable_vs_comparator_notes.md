# Java `Comparable` vs `Comparator` (Easy & Detailed Notes)

In Java, when we want to sort primitive data types (like `int`, `char`, `double`) or `String`s, Java knows how to sort them automatically using `Arrays.sort()` or `Collections.sort()`. 

However, when we have **custom objects** (like `Student`, `Employee`, or `Product`), Java doesn't know *how* to sort them (e.g., should it sort students by ID, Name, or Marks?). To solve this, Java provides two interfaces: **`Comparable`** and **`Comparator`**.

---

## 1. What is `Comparable`?

* **Package:** `java.lang`
* **Purpose:** Defines the **natural (default) ordering** of objects.
* **Method:** `public int compareTo(T obj)`
* **How it works:** The class itself must implement the `Comparable` interface and override the `compareTo()` method. It compares the current object (`this`) with the passed object (`obj`).

### Return Value Rules for `compareTo(T obj)`:
* **Positive integer (`> 0`):** Current object (`this`) is **greater** than `obj` (swaps them).
* **Negative integer (`< 0`):** Current object (`this`) is **smaller** than `obj` (keeps order).
* **Zero (`0`):** Both objects are **equal**.

### Example of `Comparable`:
Suppose we want students to be sorted by their `rollNo` by default.

```java
import java.util.*;

// Step 1: Implement Comparable interface
class Student implements Comparable<Student> {
    int rollNo;
    String name;

    public Student(int rollNo, String name) {
        this.rollNo = rollNo;
        this.name = name;
    }

    // Step 2: Override compareTo method for natural sorting (by rollNo)
    @Override
    public int compareTo(Student other) {
        // Ascending order
        return this.rollNo - other.rollNo; 
        // Note: For descending, use: other.rollNo - this.rollNo
    }

    @Override
    public String toString() {
        return rollNo + " - " + name;
    }
}

public class ComparableDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(103, "Amit"));
        list.add(new Student(101, "Riya"));
        list.add(new Student(102, "Zaid"));

        // Uses Student's compareTo() method automatically
        Collections.sort(list);

        System.out.println("Sorted by Roll No (Natural Order):");
        for (Student s : list) {
            System.out.println(s);
        }
    }
}
```
**Output:**
```text
Sorted by Roll No (Natural Order):
101 - Riya
102 - Zaid
103 - Amit
```

---

## 2. What is `Comparator`?

* **Package:** `java.util`
* **Purpose:** Defines **custom (external) ordering** of objects.
* **Method:** `public int compare(T obj1, T obj2)`
* **How it works:** You create a separate class (or use a Lambda expression) that implements `Comparator`. It compares two different objects (`obj1` and `obj2`) passed to it.
* **When to use:** 
  1. When you want **multiple sorting criteria** (e.g., sort by Name, then by Age, then by Salary).
  2. When you **cannot modify the original class** (e.g., it comes from a third-party library).

### Return Value Rules for `compare(T obj1, T obj2)`:
* **Positive integer (`> 0`):** `obj1` is **greater** than `obj2`.
* **Negative integer (`< 0`):** `obj1` is **smaller** than `obj2`.
* **Zero (`0`):** `obj1` and `obj2` are **equal**.

### Example of `Comparator`:
Using the same `Student` scenario, let's sort students by **Name** and by **Marks** without touching the `Student` class.

```java
import java.util.*;

class Student {
    int rollNo;
    String name;
    int marks;

    public Student(int rollNo, String name, int marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return rollNo + " | " + name + " | Marks: " + marks;
    }
}

// Custom Comparator 1: Sort by Name
class NameComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s1.name.compareTo(s2.name); // Uses String's built-in compareTo
    }
}

// Custom Comparator 2: Sort by Marks (Descending)
class MarksComparator implements Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return s2.marks - s1.marks; // Higher marks first
    }
}

public class ComparatorDemo {
    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student(103, "Amit", 85));
        list.add(new Student(101, "Riya", 95));
        list.add(new Student(102, "Zaid", 78));

        // 1. Sort by Name
        Collections.sort(list, new NameComparator());
        System.out.println("Sorted by Name:");
        list.forEach(System.out::println);

        // 2. Sort by Marks
        Collections.sort(list, new MarksComparator());
        System.out.println("\nSorted by Marks (Highest First):");
        list.forEach(System.out::println);

        // 3. Modern Java 8+ Shortcut (Using Lambda - No extra class needed!)
        Collections.sort(list, (s1, s2) -> s1.rollNo - s2.rollNo);
        System.out.println("\nSorted by Roll No (Using Lambda):");
        list.forEach(System.out::println);
    }
}
```
**Output:**
```text
Sorted by Name:
103 | Amit | Marks: 85
101 | Riya | Marks: 95
102 | Zaid | Marks: 78

Sorted by Marks (Highest First):
101 | Riya | Marks: 95
103 | Amit | Marks: 85
102 | Zaid | Marks: 78

Sorted by Roll No (Using Lambda):
101 | Riya | Marks: 95
102 | Zaid | Marks: 78
103 | Amit | Marks: 85
```

---

## 3. Key Differences (`Comparable` vs `Comparator`)

| Feature | `Comparable` | `Comparator` |
| :--- | :--- | :--- |
| **Package** | `java.lang` | `java.util` |
| **Sorting Logic** | Defines **default/natural** sorting order. | Defines **custom/multiple** sorting orders. |
| **Method Used** | `compareTo(Object o)` | `compare(Object o1, Object o2)` |
| **Number of Arguments** | Takes **1 argument** (`this` vs `o`). | Takes **2 arguments** (`o1` vs `o2`). |
| **Class Modification** | **Modifies** the original class (class must implement it). | **Does not modify** the original class (separate logic). |
| **Sorting Sequences** | Allows only **single** sorting sequence. | Allows **multiple** sorting sequences. |
| **How to Call** | `Collections.sort(list)` | `Collections.sort(list, comparator)` |
| **Java 8 Functional Interface?** | No (though it has 1 method, it's not annotated as `@FunctionalInterface`). | **Yes** (`@FunctionalInterface`), easily used with Lambdas. |

---

## 4. Easy Memory Trick (Analogy)

* **`Comparable` ("I can compare myself"):** Think of a person standing in line who knows their own height and compares themselves directly to the person next to them.
* **`Comparator` ("A judge comparing two people"):** Think of a third-party judge holding a clipboard who looks at *Person A* and *Person B* and decides who goes first based on whatever rule the judge wants (age, height, or shirt color).

---

## 5. Java 8+ Pro-Tip (`Comparator.comparing`)
In modern Java, you rarely need to write separate `Comparator` classes. You can chain comparators cleanly:

```java
// Sort by Name, and if two students have the same Name, sort by Marks:
list.sort(Comparator.comparing((Student s) -> s.name)
                    .thenComparingInt(s -> s.marks));
```