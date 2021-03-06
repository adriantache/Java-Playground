package com.adriantache.generics;

import com.adriantache.utils.Utils;

import static com.adriantache.utils.Utils.printDescription;
import static com.adriantache.utils.Utils.takeInput;

//generic interface
interface Containment<T extends Number> {
    boolean contains(T o);
}

public class Generics {
    private Generics() {
        throw new IllegalStateException("Utility class");
    }

    public static void main() {
        printDescription("This is a program that is used to play around \n" +
                "with generics.");

        System.out.println("Choose an option:\n" +
                "1.  Sample Generics\n" +
                "2.  Bounded Types\n" +
                "3.  Generic Methods\n");

        String input = takeInput(false);

        System.out.println();

        //process input
        switch (input) {
            case "1":
                sampleGenerics();
                break;
            case "2":
                boundedTypes();
                break;
            case "3":
                genericMethods();
                break;
            default:
                System.out.println("Illegal option!");
        }

        //go to main
        Utils.backToMain();
    }

    private static void genericMethods() {
        double x = 1.2D;
        byte b = 7;

        System.out.println("Int value of " + x + " & " + b +
                " : " + getIntValue(x) + " & " + getIntValue(b));
    }

    //generic constructor of the syntax: private static <T> int name(T t)
    private static <T extends Number> int getIntValue(T x) {
        return x.intValue();
    }

    private static void sampleGenerics() {
        System.out.println("Playing around with generics.");

        //defining an int that will get autoboxed to Integer
        //cannot use primitives! (because we can't pass them by reference)
        int i = 1;
        Gen<Integer> gen1 = new Gen<>(i);
        System.out.println("Generic " + gen1.getType() + " with value: " + gen1.getObject());

        String s = "String!";
        Gen<String> gen2 = new Gen<>(s);
        System.out.println("Generic " + gen2.getType() + " with value: " + gen2.getObject());

        //both generic type arguments can be same type
        TwoGen<Integer, String> gen3 = new TwoGen<>(i, s);
        System.out.println("Generic " + gen3.getTypes() + " with values: "
                + gen3.getObject1() + ", " + gen3.getObject2());
    }

    private static void boundedTypes() {
        System.out.println("This class only accepts numbers as type input.");
        int i = 1;
        double d = 2.1d;

        MathStuff<Integer> gInt = new MathStuff<>(i);
        MathStuff<Double> gDouble = new MathStuff<>(d);

        System.out.println("We took the numbers " + gInt.getObject() + " and " + gDouble.getObject()
                + " and we split them in half to get results " + gInt.splitInHalf() + " and "
                + gDouble.splitInHalf());

        try {
            System.out.println("We also divided the first by the latter and got the result "
                    + gInt.divide(gDouble));
        } catch (IllegalArgumentException e) {
            System.out.println("We also divided the first by the latter but couldn't!");
        }
    }

}

class Gen<T> {
    private T object;

    Gen(T obj) {
        object = obj;
    }

    T getObject() {
        return object;
    }

    String getType() {
        return object.getClass().getName();
    }
}

class TwoGen<T, V> {
    private T object;
    private V object2;

    TwoGen(T obj, V obj2) {
        object = obj;
        object2 = obj2;
    }

    T getObject1() {
        return object;
    }

    V getObject2() {
        return object2;
    }

    String getTypes() {
        return object.getClass().getName() + ", " + object2.getClass().getName();
    }
}

//can also use a pair <T, V extends T> (also works if both types are the same)
class MathStuff<T extends Number> {
    private T object;

    MathStuff(T obj) {
        object = obj;
    }

    T getObject() {
        return object;
    }

    //since this is numeric we can manually box it into a double
    double splitInHalf() {
        return object.doubleValue() / 2;
    }

    //todo learn common exceptions
    double divide(MathStuff<? extends Number> divideBy) throws IllegalArgumentException {
        if (divideBy.object.doubleValue() == 0) throw new IllegalArgumentException("Divide by zero error!");
        else return object.doubleValue() / divideBy.object.doubleValue();
    }
}

//if a class implements a generic interface it must also be generic
class MyClass<T extends Number> implements Containment<T> {
    T[] array;

    MyClass(T[] array) {
        this.array = array;
    }

    @Override
    public boolean contains(T o) {
        for (T object : array) {
            if (object.equals(o)) return true;
        }

        return false;
    }
}