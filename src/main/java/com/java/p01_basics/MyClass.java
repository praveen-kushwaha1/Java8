package com.java.p01_basics;

/**
 * ==========================================================
 * APPROACH 1: TRADITIONAL RUNNABLE IMPLEMENTATION
 * ==========================================================
 * - Best used when the task logic is complex, requires state,
 *   or needs to be reused across multiple parts of the application.
 * - Implementing Runnable is preferred over extending the Thread
 *   class because Java supports implementing multiple interfaces
 *   (avoiding single-inheritance limitations).
 */
public class MyClass implements Runnable {

    @Override
    public void run() {
        // This code executes concurrently when the thread starts.
        System.out.println("MyClass is running on thread: " + Thread.currentThread().getName());
    }
}