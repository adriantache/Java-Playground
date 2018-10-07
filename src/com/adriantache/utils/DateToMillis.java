package com.adriantache.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.adriantache.utils.Utils.backToMain;
import static com.adriantache.utils.Utils.takeInput;

public class DateToMillis {
    private DateToMillis() {
        throw new IllegalStateException("Class should not be instantiated!");
    }

    //todo add reverse function, including time
    //todo add automatic detection of time input

    public static void main() {
        System.out.println("This is a method that takes a date of the form 30-01-2018\n" +
                "and turns it into milliseconds since epoch.");

        String input = takeInput(true);
        long millis = -1;

        if (!input.isEmpty()) {
            millis = dateToMillis(input);
        } else System.out.println("Problem with input string.");

        if (millis != -1) System.out.println("Result in millis: " + millis);
        else System.out.println("Problem with conversion.");

        backToMain();
    }

    private static long dateToMillis(String date) {
        Date formattedDate = convertDate(date);

        if (formattedDate != null) return formattedDate.getTime();

        return -1;
    }

    private static Date convertDate(String date) {
        Date formattedDate = null;

        try {
            formattedDate = new SimpleDateFormat("dd-MM-yyyy", Locale.US).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formattedDate;
    }
}
