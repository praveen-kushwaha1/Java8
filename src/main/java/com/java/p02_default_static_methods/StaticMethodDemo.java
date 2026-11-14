package com.java.p02_default_static_methods;

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
//A static method belongs to the interface itself, not to the implementing class or its object.

//Important interview points:
//        - Call static interface methods using InterfaceName.methodName().
//        - They are not inherited by implementing classes.
//- They cannot be overridden.
//        - You cannot call them through an implementing class object