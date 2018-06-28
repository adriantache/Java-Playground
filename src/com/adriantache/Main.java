package com.adriantache;

import com.adriantache.bouncing_ball.BouncingBall;
import com.adriantache.palindrome.Palindrome;
import com.adriantache.sorting_algorithms.SortingAlgorithms;
import com.adriantache.stack_queue.StackQueue;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        char input = '!';

        System.out.println("Choose an option:\n" +
                "1. Bouncing Ball\n" +
                "2. Stack/Queue\n" +
                "3. Sorting Algorithms\n" +
                "4. Palindrome\n");

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
            case '3':
                SortingAlgorithms.main();
                break;
            case '4':
                Palindrome.main();
                break;
            default:
                System.out.println("Illegal option!");
        }
    }
}
