package com.java.p04_method_references;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Demonstrates instance method references.
 *
 * An instance method reference uses an existing object
 * to invoke its instance method.
 */
public class InstanceMethodReference {

    // Instance method
    public void display(String message) {
        System.out.println("Message: " + message);
    }

    // Instance method returning a String
    public String getWelcomeMessage() {
        return "Welcome to Java Method References";
    }

    public static void main(String[] args) {

        // Creating an object
        InstanceMethodReference obj =
                new InstanceMethodReference();

        // Lambda expression
        Consumer<String> lambda =
                message -> obj.display(message);

        // Instance method reference
        Consumer<String> methodReference =
                obj::display;

        lambda.accept("Using Lambda");

        methodReference.accept("Using Method Reference");

        // Instance method reference returning a value
        Supplier<String> supplier =
                obj::getWelcomeMessage;

        System.out.println(supplier.get());
    }
}
