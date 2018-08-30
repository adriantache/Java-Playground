package com.adriantache.stream_practice;

import com.adriantache.utils.Utils;

import java.io.IOException;

import static com.adriantache.utils.Utils.printDescription;

public class StreamPractice {
    private StreamPractice() {
        throw new IllegalStateException("Utility class");
    }

    public static void main() {
        printDescription("This is a program that is used to play around \n" +
                "with various types of streams and their functionality.");

        System.out.println("Choose an option:\n" +
                "1. Read an array of bytes\n" +
                "2. \n" +
                "3. \n" +
                "4. \n" +
                "5. \n" +
                "6. \n");

        char input = '\n';

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
                readArrayOfBytes();
                break;
            case '2':

                break;
            case '3':

                break;
            case '4':

                break;
            case '5':

                break;
            case '6':

                break;
            default:
                System.out.println("Illegal option!");
        }


        //go to main
        Utils.backToMain();
    }

    private static void readArrayOfBytes() {
        byte[] data = new byte[10];
        System.out.println("Enter some characters. (10 max)");

        try {
            System.in.read(data);

            System.out.print("You entered: ");
            for (int i = 0; i < data.length; i++) {
                System.out.print((char) data[i]);
            }

            //exhaust buffer if input was >10 chars
            char buffer = '!';
            while (buffer != '\n')
                buffer = (char) System.in.read();
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }

}
