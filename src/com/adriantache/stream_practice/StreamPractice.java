package com.adriantache.stream_practice;

import com.adriantache.utils.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
                "2. Print format\n" +
                "3. File operations\n" +
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
                printf();
                break;
            case '3':
                fileOperations();
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
        System.out.println("Enter some characters. (10 max)");

        try {
            byte[] data = new byte[10];
            int bytesAvailable = System.in.available();
            int bytesRead = System.in.read(data);

            System.out.println(bytesRead + " bytes read out of " + bytesAvailable + " bytes available.");

            System.out.print("You entered: ");
            for (int i = 0; i < data.length; i++) {
                System.out.print((char) data[i]);
            }

            //exhaust buffer if input was >10 chars
            char buffer = (char) System.in.read();
            while (buffer != '\n')
                buffer = (char) System.in.read();
        } catch (IOException e) {
            System.out.println("Error.");
        }
    }

    private static void printf() {
        System.out.println("Printing characters using a format.");

        System.out.printf("%4$2s %3$2s %2$2s %1$2s", "a", "b", "c", "d");
        System.out.println();
        System.out.format("%4$2s %3$2s %2$2s %1$2s", "a", "b", "c", "d");
    }

    private static void fileOperations() {
        final String FILENAME = "TEST.TXT";
        final String OUTPUT = "This is a test.\n";

        System.out.println("Writing \"" + OUTPUT.subSequence(0, OUTPUT.length() - 1) + "\" to " + FILENAME + ".");
        FileOutputStream fOut = null;

        try {
            //using append to create an amusingly long file, otherwise it overwrites by default!
            fOut = new FileOutputStream(FILENAME, true);
            for (int i = 0; i < OUTPUT.length(); i++) {
                fOut.write(OUTPUT.charAt(i));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot create/find file!");
        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        } finally {
            try {
                if (fOut != null) fOut.close();
            } catch (IOException e) {
                System.out.println("Cannot close file!");
            }
        }

        System.out.println("Reading from " + FILENAME + ".");
        FileInputStream fIn = null;

        try {
            fIn = new FileInputStream(FILENAME);
            int i;
            do {
                i = fIn.read();
                if (i != -1) System.out.print((char) i);
            } while (i != -1);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file!");
        } catch (IOException e) {
            System.out.println("Cannot read from file!");
        } finally {
            try {
                if (fIn != null) fIn.close();
            } catch (IOException e) {
                System.out.println("Cannot close file!");
            }
        }
    }

}
