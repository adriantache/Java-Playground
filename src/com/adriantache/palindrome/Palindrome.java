package com.adriantache.palindrome;

import static com.adriantache.utils.Utils.*;

public class Palindrome {
    public static void main() {
        printDescription("This is a program that accepts a user input \n" +
                "and verifies if it is a palindrome.");

        String input = takeInput(true);

        if (input.length() == 0) {
            System.out.println("Illegal input!");
        } else if (testPalindrome(input)) {
            System.out.println("Input \"" + input + "\" is a palindrome.");
        } else if (!testPalindrome(input)) {
            System.out.println("Input \"" + input + "\" is NOT a palindrome.");
        }

        backToMain();
    }

    private static boolean testPalindrome(String s) {
        int length = s.length();

        for (int i = 0; i <= length / 2 - 1; i++) {
            if (s.charAt(i) != s.charAt(--length)) return false;
        }

        return true;
    }
}
