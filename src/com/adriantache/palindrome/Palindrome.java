package com.adriantache.palindrome;

import java.io.IOException;

public class Palindrome {
    public static void main() {
        char input;
        StringBuilder sb = new StringBuilder();

        System.out.println("This is a program that accepts a user input ");
        System.out.println("and verifies if the number is a palindrome.");
        System.out.println();

        //get user input
        try {
            do {
                input = (char) System.in.read();
                if (input != '\n') sb.append(input);
            } while (input != '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (testPalindrome(sb.toString())) {
            System.out.println("Number " + sb + " is a palindrome.");
        } else {
            System.out.println("Number " + sb + " is NOT a palindrome.");
        }
    }

    private static boolean testPalindrome(String s) {
        int length = s.length();

        for (int i = 0; i < length / 2 - 1; i++) {
            if (s.charAt(i) != s.charAt(--length)) return false;
        }

        return true;
    }
}
