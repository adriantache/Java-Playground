package com.adriantache.stream_practice;

import com.adriantache.utils.Utils;

import java.io.*;
import java.util.Arrays;

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
                "4. Data Streams\n" +
                "5. Console input\n" +
                "6. Character based streams\n");

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
                dataStreams();
                break;
            case '5':
                consoleInput();
                break;
            case '6':
                characterBasedStreams();
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

        //replacing this try to a "try-with-resources"
        System.out.println("Reading from " + FILENAME + ":");
        try (FileInputStream fIn = new FileInputStream(FILENAME)) {
            int i;
            do {
                i = fIn.read();
                if (i != -1) System.out.print((char) i);
            } while (i != -1);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file!");
        } catch (IOException e) {
            System.out.println("Cannot read from file!");
        }
    }

    private static void dataStreams() {
        final String FILENAME = "TEST.RAW";
        final boolean OUTPUT_BOOL = true;
        final double OUTPUT_DOUBLE = 0.0556d;
        final int OUTPUT_INT = 4;

        System.out.println("Writing \"" + Boolean.toString(OUTPUT_BOOL) + ", " + Double.toString(OUTPUT_DOUBLE) +
                ", " + Integer.toString(OUTPUT_INT) + "\" to " + FILENAME + ".");

        try (DataOutputStream dataOutputStream = new DataOutputStream(new FileOutputStream(FILENAME))) {
            dataOutputStream.writeBoolean(OUTPUT_BOOL);
            dataOutputStream.writeDouble(OUTPUT_DOUBLE);
            dataOutputStream.writeInt(OUTPUT_INT);
        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        }

        System.out.println("Reading from " + FILENAME + ":");

        try (DataInputStream dataInputStream = new DataInputStream(new FileInputStream(FILENAME))) {
            System.out.println("Boolean: \t" + Boolean.toString(dataInputStream.readBoolean()));
            System.out.println("Double: \t" + Double.toString(dataInputStream.readDouble()));
            System.out.println("Int: \t\t" + Integer.toString(dataInputStream.readInt()));
        } catch (IOException e) {
            System.out.println("Cannot read from file!");
        }
    }

    private static void consoleInput() {
        System.out.println("Please input a \"password\":");
        char[] password = null;

        Console console = System.console();

        if (console == null) {
            System.out.println("ERROR: Cannot obtain console.");
            return;
        }

        try {
            password = console.readPassword();
        } catch (IOError e) {
            System.out.println("Error reading password.");
        }

        System.out.println("Your password was: " + Arrays.toString(password));
    }

    private static void characterBasedStreams(){
        PrintWriter printWriter = new PrintWriter(System.out, true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            printWriter.println("Enter a string:");
            char c;
            do{
                c = (char) bufferedReader.read();
                printWriter.print(c);
            } while (c!='\n');

            printWriter.println("\nEnter lines of text: (type stop to... stop)");
            String s = "";
            do {
                printWriter.println(s);
                s = bufferedReader.readLine();
            } while (!s.equalsIgnoreCase("stop"));
        } catch (IOException e) {
            printWriter.println("Error reading input.");
        }
    }

}
