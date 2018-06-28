package com.adriantache.utils;

import com.adriantache.Main;

import java.io.IOException;

public class Utils {
    public static void backToMain(){
        System.out.println();
        System.out.println("Go back to main menu? (y/n)");

        char input = '\n';

        try {
             input = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();

        if (input=='y') Main.main(null);
    }
}
