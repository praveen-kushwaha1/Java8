package com.java.p02_default_static_methods;

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
         *
         * Java cannot automatically decide which default
         * implementation should be used.
         *
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

        // Calls overridden default method.
        car.stop();

        // Calls inherited default method.
        car.displayInfo();


        // --------------------------------------------------
        // EXAMPLE 2: STATIC METHOD
        // --------------------------------------------------

        System.out.println("\n--- STATIC METHOD ---");

        // Correct: call static method using interface name.
        System.out.println("Addition: "
                + MathUtils.add(10, 20));

        System.out.println("Multiplication: "
                + MathUtils.multiply(5, 4));


        // --------------------------------------------------
        // EXAMPLE 3: MULTIPLE INHERITANCE
        // --------------------------------------------------

        System.out.println("\n--- MULTIPLE INHERITANCE ---");

        SmartPhone phone = new SmartPhone();

        phone.takePhoto();

        phone.playMusic();

        // Uses overridden conflict-resolution method.
        phone.deviceInfo();


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
