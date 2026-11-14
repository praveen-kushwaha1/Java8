package com.java.p01_basics;

/**
 * ==========================================================
 * MULTITHREADING REVISION: THREAD CREATION STYLES
 * ==========================================================
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Main thread started: " + Thread.currentThread().getName());

        // --------------------------------------------------
        // STYLE 1: Traditional Object Instantiation
        // --------------------------------------------------
        // Step 1: Create an instance of the Runnable implementation.
        MyClass myClassTask = new MyClass();

        // Step 2: Pass the Runnable task to a Thread object wrapper.
        Thread thread1 = new Thread(myClassTask);

        // Step 3: Start the thread.
        // NOTE: Always call start(), NEVER call run() directly if you want concurrency.
        thread1.start();


        // --------------------------------------------------
        // STYLE 2: Java 8 Lambda Expression (Clean & Modern)
        // --------------------------------------------------
        // Since Runnable has only one method (run), Java allows lambda syntax
        // to pass the behavior directly without writing a separate class file.
        Runnable lambdaTask = () -> {
            System.out.println("Lambda Runnable is running on thread: " + Thread.currentThread().getName());
        };

        Thread thread2 = new Thread(lambdaTask);
        thread2.start();


        // --------------------------------------------------
        // STYLE 3: Inline Lambda Runnable (Most Concise)
        // --------------------------------------------------
        // Combines task definition and thread startup into a single statement.
        // Ideal for short, throwaway background tasks.
        new Thread(() -> {
            System.out.println("Inline Lambda Runnable is running on thread: " + Thread.currentThread().getName());
        }).start();

        System.out.println("Main thread finished execution.");
    }
}