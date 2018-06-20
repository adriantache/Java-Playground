package com.adriantache;

import com.adriantache.bouncing_ball.BouncingBall;
import com.adriantache.stack_queue.StackQueue;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        char input = '!';

        System.out.println("Choose an option:\n1. Bouncing Ball\n2. Stack/Queue\n");

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
            case '2':
                StackQueue.main();
                break;
            default:
                System.out.println("Illegal option!");
        }
    }
}
