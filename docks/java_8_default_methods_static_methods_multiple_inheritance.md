# Java 8 – Default Methods, Static Methods & Multiple Inheritance

**Package:** `p09_default_static_methods`

## Topics Covered
1. Default methods in interfaces.
2. Static methods in interfaces.
3. Multiple inheritance using interfaces.
4. Default method conflicts and how to resolve them.
5. Interface methods vs class methods.
6. Interview questions and important rules.

---

## 1. Default Methods
A **default method** is an interface method that has a body (implementation). It was introduced in Java 8 to allow developers to add new methods to existing interfaces without forcing every implementing class to implement those methods (maintaining backward compatibility).

### Example
```java
interface Vehicle {
    // Default method: interface method with implementation.
    default void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car implements Vehicle {
    // No need to override the default method.
}

public class DefaultMethodDemo {
    public static void main(String[] args) {
        Car car = new Car();

        // Calling inherited default method using object.
        car.start();
    }
}
```

**Output:**
```text
Vehicle is starting
```

> **Important:** A class can override a default method if it wants its own custom implementation.

---

## 2. Static Methods in Interfaces
Java 8 allows interfaces to contain **static methods** with a body. A static method belongs to the interface itself, **not** to the implementing class or its object.

### Example
```java
interface MathUtils {
    // Static method inside interface.
    static int add(int a, int b) {
        return a + b;
    }
}

public class StaticMethodDemo {
    public static void main(String[] args) {
        // Call using interface name.
        int result = MathUtils.add(10, 20);
        System.out.println("Sum: " + result);
    }
}
```

**Output:**
```text
Sum: 30
```

### Important Interview Points
* Call static interface methods using `InterfaceName.methodName()`.
* They are **not inherited** by implementing classes.
* They **cannot be overridden**.
* You **cannot** call them through an implementing class object.

---

## 3. Multiple Inheritance Using Interfaces
Java does not support multiple inheritance through classes (e.g., `class C extends A, B {}` causes a compilation error). However, Java supports **multiple inheritance of type** through interfaces.

### Simple Example
```java
interface Camera {
    void takePhoto();
}

interface MusicPlayer {
    void playMusic();
}

class SmartPhone implements Camera, MusicPlayer {
    @Override
    public void takePhoto() {
        System.out.println("Taking photo");
    }

    @Override
    public void playMusic() {
        System.out.println("Playing music");
    }
}
```

### Important: Default Method Conflict
What happens when two interfaces contain the same default method signature?

```java
interface A {
    default void show() {
        System.out.println("Show from A");
    }
}

interface B {
    default void show() {
        System.out.println("Show from B");
    }
}

class MyClass implements A, B {
    // Mandatory: resolve the conflict.
    @Override
    public void show() {
        A.super.show();
        B.super.show();
        System.out.println("Show from MyClass");
    }
}

public class MultipleInheritanceDemo {
    public static void main(String[] args) {
        MyClass obj = new MyClass();
        obj.show();
    }
}
```

**Output:**
```text
Show from A
Show from B
Show from MyClass
```

#### Why do we override `show()`?
Because both interfaces provide an implementation of the same method, Java cannot automatically decide which implementation to use. We resolve the conflict using:
* `A.super.show()` $\rightarrow$ Calls interface `A`'s default implementation.
* `B.super.show()` $\rightarrow$ Calls interface `B`'s default implementation.

---

## 4. Complete Runnable Revision Class
Save this file as `Java8InterfaceMethodsRevision.java` to run and revise all concepts together.

```java
package com.learning.java8.p09_default_static_methods;

/**
 * ==========================================================
 * JAVA 8 - DEFAULT METHODS, STATIC METHODS AND MULTIPLE
 * INHERITANCE
 * ==========================================================
 *
 * Topics:
 * 1. Default methods in interfaces
 * 2. Overriding default methods
 * 3. Static methods in interfaces
 * 4. Multiple inheritance using interfaces
 * 5. Default method conflict
 * 6. Resolving conflicts using InterfaceName.super
 * 7. Calling static interface methods
 * 8. Important interview rules
 */
public class Java8InterfaceMethodsRevision {

    // ======================================================
    // 1. DEFAULT METHOD
    // ======================================================
    interface Vehicle {
        // Abstract method: implementing class must implement it.
        void start();

        // Default method: interface provides implementation.
        default void stop() {
            System.out.println("Vehicle stopped");
        }

        // Another default method.
        default void displayInfo() {
            System.out.println("This is a vehicle");
        }
    }

    // Implementing Vehicle interface.
    static class Car implements Vehicle {
        @Override
        public void start() {
            System.out.println("Car started");
        }

        // We can override default methods optionally.
        @Override
        public void stop() {
            System.out.println("Car stopped");
        }
    }

    // ======================================================
    // 2. STATIC METHOD IN INTERFACE
    // ======================================================
    interface MathUtils {
        // Static methods belong to the interface itself.
        static int add(int a, int b) {
            return a + b;
        }

        static int multiply(int a, int b) {
            return a * b;
        }
    }

    // ======================================================
    // 3. MULTIPLE INHERITANCE USING INTERFACES
    // ======================================================
    interface Camera {
        void takePhoto();

        default void deviceInfo() {
            System.out.println("Camera interface");
        }
    }

    interface MusicPlayer {
        void playMusic();

        default void deviceInfo() {
            System.out.println("MusicPlayer interface");
        }
    }

    // One class implements two interfaces.
    static class SmartPhone implements Camera, MusicPlayer {
        @Override
        public void takePhoto() {
            System.out.println("Taking photo");
        }

        @Override
        public void playMusic() {
            System.out.println("Playing music");
        }

        /*
         * Both interfaces contain deviceInfo().
         * Java cannot automatically decide which default
         * implementation should be used.
         * Therefore, we MUST override the conflicting method.
         */
        @Override
        public void deviceInfo() {
            // Explicitly call Camera's default implementation.
            Camera.super.deviceInfo();

            // Explicitly call MusicPlayer's implementation.
            MusicPlayer.super.deviceInfo();

            System.out.println("SmartPhone device information");
        }
    }

    // ======================================================
    // 4. DEFAULT METHOD OVERRIDING
    // ======================================================
    interface Bank {
        default void welcomeMessage() {
            System.out.println("Welcome to the bank");
        }
    }

    static class SBI implements Bank {
        // Override the default method with bank-specific logic.
        @Override
        public void welcomeMessage() {
            System.out.println("Welcome to SBI Bank");
        }
    }

    // ======================================================
    // 5. MAIN METHOD
    // ======================================================
    public static void main(String[] args) {

        // --------------------------------------------------
        // EXAMPLE 1: DEFAULT METHOD
        // --------------------------------------------------
        System.out.println("\n--- DEFAULT METHOD ---");
        Car car = new Car();
        car.start();
        car.stop();        // Calls overridden default method.
        car.displayInfo(); // Calls inherited default method.

        // --------------------------------------------------
        // EXAMPLE 2: STATIC METHOD
        // --------------------------------------------------
        System.out.println("\n--- STATIC METHOD ---");
        // Correct: call static method using interface name.
        System.out.println("Addition: " + MathUtils.add(10, 20));
        System.out.println("Multiplication: " + MathUtils.multiply(5, 4));

        // --------------------------------------------------
        // EXAMPLE 3: MULTIPLE INHERITANCE
        // --------------------------------------------------
        System.out.println("\n--- MULTIPLE INHERITANCE ---");
        SmartPhone phone = new SmartPhone();
        phone.takePhoto();
        phone.playMusic();
        phone.deviceInfo(); // Uses overridden conflict-resolution method.

        // --------------------------------------------------
        // EXAMPLE 4: DEFAULT METHOD OVERRIDING
        // --------------------------------------------------
        System.out.println("\n--- DEFAULT METHOD OVERRIDING ---");
        SBI sbi = new SBI();
        sbi.welcomeMessage();

        // --------------------------------------------------
        // EXAMPLE 5: CALL DEFAULT METHOD WITHOUT OVERRIDING
        // --------------------------------------------------
        System.out.println("\n--- INHERITED DEFAULT METHOD ---");
        Bank bank = new Bank() {
            // No need to implement welcomeMessage().
            // Bank already provides its default implementation.
        };
        bank.welcomeMessage();

        System.out.println("\nJava 8 Interface Revision Completed!");
    }
}
```

---

## 5. Important Java 8 Interview Rules

| Concept | Important Rule |
| :--- | :--- |
| **Default method** | Can be inherited and overridden. |
| **Static interface method** | Called using the interface name (`InterfaceName.method()`). |
| **Static interface method** | Not inherited by the implementing class. |
| **Multiple interfaces** | A class can implement multiple interfaces. |
| **Default method conflict** | The implementing class must override the conflicting method. |
| **`InterfaceName.super`** | Calls a specific interface's default method. |
| **Class method vs default method** | Class implementation always takes priority. |

---

## 6. Three Important Interview Scenarios

1. **Scenario 1: What if a class already has a method with the same signature as an interface default method?**
   * **Answer:** The class method takes priority (the "class wins" rule). The interface default implementation is ignored.
2. **Scenario 2: What if one interface extends another interface and overrides its default method?**
   * **Answer:** The child (sub-interface) default method takes priority over the parent interface's method.
3. **Scenario 3: What if two unrelated interfaces have the same default method?**
   * **Answer:** Compilation fails unless the implementing class overrides the method and explicitly resolves the conflict.

---

## 7. Quick Revision Cheat Sheet

* **Default Method (Java 8)**
  * Has a method body.
  * Called using an object instance.
  * Can be overridden.
* **Static Method (Java 8)**
  * Has a method body.
  * Called using the interface name.
  * Cannot be overridden.
* **Multiple Inheritance (Interfaces)**
  * A class can implement multiple interfaces.
  * Conflicting default methods must be resolved.
  * Use `InterfaceName.super.methodName()`.

---

## 8. Practice Questions (Knowledge Check)

**1. Can an interface have multiple default methods?**
* **Answer:** **Yes.** An interface can declare multiple default methods, provided their method signatures do not conflict.

**2. Can we call an interface static method using an implementing class object?**
* **Answer:** **No.** Interface static methods belong only to the interface and must be called using the interface name, not an implementing object.

**3. What happens when two interfaces have the same default method?**
* **Answer:** **Compilation error unless the class resolves the conflict.** The implementing class must override the conflicting method.

**4. Which syntax calls a specific interface default method?**
* **Answer:** `A.super.show()` (General syntax: `InterfaceName.super.methodName()`).