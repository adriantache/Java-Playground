package com.adriantache.misc;

import com.adriantache.utils.Utils;

import java.util.Calendar;
import java.util.TimeZone;

import static com.adriantache.utils.Utils.printDescription;
import static com.adriantache.utils.Utils.takeInput;

public class Misc {
    private Misc() {
        throw new IllegalStateException("Utility class");
    }

    public static void main() {
        printDescription("This is a sandbox.");

        System.out.println("Choose an option:\n" +
                "1.  Debug Input\n" +
                "2.  Java Question\n" +
                "3.  Date Test\n");

        String input = takeInput(false);

        System.out.println();

        //process input
        switch (input) {
            case "1":
                debugInput();
                break;
            case "2":
                question();
                break;
            case "3":
                dateTest();
                break;
            default:
                System.out.println("Illegal option!");
        }

        //go to main
        Utils.backToMain();
    }


    private static void debugInput() {
        String s;

        System.out.println("Please input stuff:\n");

        for (int i = 0; i < 9; i++) {
            s = takeInput(true).trim().toUpperCase();

            if (s.equals("X")) break;

            System.out.println("Input detected: " + s);
        }
    }

    private static void question() {
        StringBuilder s1 = new StringBuilder("Java");
        System.out.println(s1);
        String s2 = "Love";
        s1.append(s2);
        System.out.println(s1);
        s1.substring(4);
        System.out.println(s1);
        int foundAt = s1.indexOf(s2);
        System.out.println(foundAt);
    }

    private static void dateTest() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("Europe/Bucharest"));
        //set target weekday to tuesday
        int weekday = 3;
        System.out.println("Target dotw: " + weekday + " - " + dayToString(weekday));

        //get the current day of the week
        int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("Current dotw: " + dayOfTheWeek + " - " + dayToString(dayOfTheWeek));

        //if it's already that weekday, do nothing, otherwise move the date to next weekday
        if (dayOfTheWeek < weekday) {
            calendar.add(Calendar.DATE, weekday - dayOfTheWeek);
        } else if (dayOfTheWeek > weekday) {
            calendar.add(Calendar.DATE, 7 - dayOfTheWeek + weekday);
        }

        //get the future day of the week
        dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("Future dotw: " + dayOfTheWeek + " - " + dayToString(dayOfTheWeek));
    }

    private static String dayToString(int dayOfTheWeek) {
        switch (dayOfTheWeek) {
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wednesday";
            case Calendar.THURSDAY:
                return "Thursday";
            case Calendar.FRIDAY:
                return "Friday";
            case Calendar.SATURDAY:
                return "Saturday";
            case Calendar.SUNDAY:
                return "Sunday";
            default:
                return "ERROR";
        }
    }
}