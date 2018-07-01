package com.adriantache.utils;

import com.adriantache.Main;

import java.io.IOException;

public class Utils {
    public static final int ERROR_VALUE = -1;

    private Utils() {
        throw new IllegalStateException("Utility class");
    }

    public static void backToMain() {
        System.out.println();
        System.out.println("Go back to main menu? (y/n)");

        if (takeInput(false).equals("y")) {
            System.out.println();
            Main.main(null);
        }
    }

    public static void printDescription(String description) {
        System.out.println(description);
        System.out.println();
    }

    public static String takeInput(boolean multipleChars) {
        char input;
        StringBuilder sb = new StringBuilder();

        //get user input
        try {
            do {
                input = (char) System.in.read();
                if (input != '\n') sb.append(input);
                if (!multipleChars) break;
            } while (input != '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
