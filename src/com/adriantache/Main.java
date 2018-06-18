package com.adriantache;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        char input = '!';

        System.out.println("Choose an option:\n1. Bouncing Ball\n");

        //get user input
        try {
            input = (char) System.in.read();
            if (input == '\n') input = (char) System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //exhaust buffer
        char buffer = '!';
        while (buffer != '\n') {
            try {
                buffer = (char) System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //process input
        switch (input) {
            case '1':
                BouncingBall.main();
                break;

            default:
                System.out.println("Illegal option!");
        }
    }
}
