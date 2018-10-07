package com.adriantache;

import com.adriantache.bouncing_ball.BouncingBall;
import com.adriantache.enum_practice.EnumPractice;
import com.adriantache.generics.Generics;
import com.adriantache.knight_chess_board.KnightChessBoard;
import com.adriantache.multithreading.MultiThreading;
import com.adriantache.palindrome.Palindrome;
import com.adriantache.sorting_algorithms.SortingAlgorithms;
import com.adriantache.stack_queue.StackQueue;
import com.adriantache.stream_practice.StreamPractice;
import com.adriantache.utils.DateToMillis;

import java.io.IOException;

import static com.adriantache.utils.Utils.backToMain;
import static com.adriantache.utils.Utils.takeInput;

public class Main {

    public static void main(String[] args) {
        char input = '!';

        System.out.println("Choose an option:\n" +
                "1. Bouncing Ball\n" +
                "2. Stack/Queue\n" +
                "3. Sorting Algorithms\n" +
                "4. Palindrome\n" +
                "5. Knight Chess Board\n" +
                "6. Stream Practice\n" +
                "7. Multithreading\n" +
                "8. Enum Practice\n" +
                "9. Generics\n" +
                "0. Date to Millis\n");

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

        System.out.println();

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
            case '5':
                KnightChessBoard.main();
                break;
            case '6':
                StreamPractice.main();
                break;
            case '7':
                MultiThreading.main();
                break;
            case '8':
                EnumPractice.main();
                break;
            case '9':
                Generics.main();
                break;
            case '0':
                DateToMillis.main();
                break;
            case 'x':
                debugInput();
                break;
            default:
                System.out.println("Illegal option!");
        }
    }

    private static void debugInput() {
        String s;

        System.out.println("Please input stuff:\n");

        for (int i = 0; i < 9; i++) {
            s = takeInput(true).trim().toUpperCase();

            if (s.equals("X")) break;

            System.out.println("Input detected: " + s);
        }

        backToMain();
    }
}
