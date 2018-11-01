package com.adriantache.lambdas;

import com.adriantache.utils.Utils;

import static com.adriantache.utils.Utils.printDescription;
import static com.adriantache.utils.Utils.takeInput;

public class Lambdas {
    private Lambdas() {
        throw new IllegalStateException("Utility class");
    }

    public static void main() {
        printDescription("This is a program that is used to play around \n" +
                "with lambdas.");

        System.out.println("Choose an option:\n" +
                "1.  Lambda overloading and generics\n" +
                "2.  \n" +
                "3.  \n");

        String input = takeInput(false);

        System.out.println();

        //process input
        switch (input) {
            case "1":
                lambdaOverloading();
                break;
            case "2":

                break;
            case "3":

                break;
            default:
                System.out.println("Illegal option!");
        }

        //go to main
        Utils.backToMain();
    }

    private static void lambdaOverloading() {
        int n = 1;
        String string = "Hello lambda!";

        System.out.println("Testing constant lambda expression.");
        //just returns 42 for any call
        EmptyFunctionalInterface emptyFunctionalInterface = () -> 42;
        System.out.println(emptyFunctionalInterface.test());

        System.out.println("\nTesting regular lambda expression.");
        //use a lambda to instance a class and define implementation for the interface
        MathTests greaterThanZero = i -> i > 0;
        System.out.println(n + " greater than zero? " + greaterThanZero.test(n));
        //use same functional interface to redefine the test method, ironically it's functionally
        //identical to the one defined above
        MathTests absValueEqual = i -> i == (i > 0 ? i : -i);
        System.out.println(n + " equal to its absolute value? " + absValueEqual.test(n));

        System.out.println("\nTesting generic lambda expression.");
        //rerun same tests with generic interface
        GenericFunctionalInterface<Integer> greaterThanZeroGeneric = i -> i > 0;
        System.out.println(n + " is greater than zero? " + greaterThanZeroGeneric.test(n));
        GenericFunctionalInterface<Integer> absValueEqualGeneric = i -> i == (i > 0 ? i : -i);
        System.out.println(n + " is equal to its absolute value? " + absValueEqualGeneric.test(n));
        //also include a String test
        GenericFunctionalInterface<String> stringTest = s -> (s == null || s.isEmpty());
        System.out.println(n + " is empty? " + stringTest.test(string));
    }

    interface EmptyFunctionalInterface {
        int test();
    }

    interface MathTests {
        boolean test(int i);
    }

    interface GenericFunctionalInterface<T> {
        boolean test(T t);
    }
}
