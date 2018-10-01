package com.adriantache.enum_practice;

import com.adriantache.multithreading.TrafficLight;
import com.adriantache.utils.Utils;

import java.util.Arrays;

import static com.adriantache.utils.Utils.printDescription;
import static com.adriantache.utils.Utils.takeInput;

public class EnumPractice {
    private EnumPractice() {
        throw new IllegalStateException("Utility class");
    }

    public static void main() {
        printDescription("This is a program that is used to play around \n" +
                "with multithreading.");

        System.out.println("Choose an option:\n" +
                "1. Enum Practice\n" +
                "2. Enum Variable\n" +
                "3. Enum Exercise\n" +
                "4. Traffic Light (duplicate)\n");

        String input = takeInput(false);

        System.out.println();

        //process input
        switch (input) {
            case "1":
                enumPractice();
                break;
            case "2":
                enumVariable();
                break;
            case "3":
                enumExercise();
                break;
            case "4":
                TrafficLight.main();
                break;
            default:
                System.out.println("Illegal option!");
        }

        //go to main
        Utils.backToMain();
    }

    private static void enumPractice() {
        Days monday;

        System.out.println("Please input a day of the week: ");

        String day = takeInput(true).trim().toUpperCase();

        System.out.println("Day value input is:" + day);

        //todo fix error here, no idea why it happens
        if (!day.isEmpty())
            monday = Days.valueOf(day);
        else monday = Days.MONDAY;

        if (monday == Days.MONDAY) System.out.println("Monday is Monday, who knew!");

        switch (monday) {
            case MONDAY:
                System.out.println("Monday is still " + Days.MONDAY + ", all is well.");
                break;
            default:
                System.out.println("Something is wrong");
        }

        System.out.println("Possible values for Days enum: " + Arrays.toString(Days.values()));

        //we can also call compareTo() to compare positional values
        for (Days d : Days.values()) {
            System.out.println("Day " + d.ordinal() + " is " + d);
        }
    }

    private static void enumVariable() {
        Transport car = Transport.CAR;
        System.out.println("Default speed for the car is " + car.getSpeed() + " MPH.");

        System.out.println("Default speed for a boat is " + Transport.BOAT.getSpeed() + " MPH.");

        System.out.println("Default speed for the rest is:");

        for (Transport t : Transport.values()) {
            if (t != Transport.BOAT && t != Transport.CAR) {
                System.out.println("Default speed for a " + t + " is " + t.getSpeed() + " MPH.");
            }
        }
    }

    private static void enumExercise() {
        for (Tools t : Tools.values()) {
            System.out.println(t + " " + t.ordinal());
        }
    }

    enum Days {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY}

    enum Transport {
        CAR(65), TRUCK(55), AIRPLANE(600), TRAIN(70), BOAT(22);

        private int speed;

        Transport(int s) {
            speed = s;
        }

        int getSpeed() {
            return speed;
        }
    }

    enum Tools {SCREWDRIVER, WRENCH, HAMMER, PLIERS}

}
