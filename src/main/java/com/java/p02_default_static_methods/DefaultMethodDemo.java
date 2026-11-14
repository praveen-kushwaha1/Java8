package com.java.p02_default_static_methods;

interface Vehicle {

    // Default method: interface method with implementation.
    default void start() {
        System.out.println("Vehicle is starting");
    }
}

class Car implements Vehicle {

    // No need to override the default method.
    //Important: A class can override a default method if it wants its own implementation.
}

public class DefaultMethodDemo {

    public static void main(String[] args) {

        Car car = new Car();

        // Calling inherited default method using object.
        car.start();
    }
}
