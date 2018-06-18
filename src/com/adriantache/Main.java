package com.adriantache;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        char input = '!';

        System.out.println("Choose an option:\n1. Bouncing Ball\n");

        //get user input
        try {
            input = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //exhaust buffer
        char buffer = '!';
        while (buffer!='\n') {
            try {
                buffer = (char) System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //process input
        if (input == '1') BouncingBall.main();
        else  System.out.println("Illegal option!");
    }
}
