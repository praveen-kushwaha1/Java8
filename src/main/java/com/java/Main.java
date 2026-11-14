package com.java;

import java.security.PrivilegedAction;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);

            Supplier<String> supplier = () -> "hello";
            System.out.println(supplier.get());
            Consumer<String> consumer = (s) -> System.out.println(s);
            consumer.accept("hello");

            Predicate<Integer> isEven = n -> n % 2 == 0;
            System.out.println(isEven.test(4)); // Output: true

            Consumer<String> printer = name -> System.out.println("Hello, " + name);
            printer.accept("Alice"); // Output: Hello, Alice

            java.util.function.Function<Integer, Integer> increment = a -> a + 1;
            System.out.println(increment.apply(5)); // Output: 6
        }
    }
}