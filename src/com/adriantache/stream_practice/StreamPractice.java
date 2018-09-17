package com.adriantache.stream_practice;

import com.adriantache.utils.Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static com.adriantache.utils.Utils.printDescription;
import static com.adriantache.utils.Utils.takeInput;

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
                "6. Character based streams\n" +
                "7. Character based IO\n" +
                "8. Help file\n" +
                "9. Scanner\n" +
                "0. Number to binary\n");

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
            case '7':
                characterBasedIO();
                break;
            case '8':
                helpFile();
                break;
            case '9':
                scanner();
                break;
            case '0':
                stringToBinary();
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

    private static void characterBasedStreams() {
        PrintWriter printWriter = new PrintWriter(System.out, true);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        try {
            printWriter.println("Enter a string:");
            char c;
            do {
                c = (char) bufferedReader.read();
                printWriter.print(c);
            } while (c != '\n');

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

    private static void characterBasedIO() {
        final String FILENAME = "TEST2.TXT";
        final String OUTPUT = "This is another test.\n";

        System.out.println("Writing \"" + OUTPUT.subSequence(0, OUTPUT.length() - 1) + "\" to " + FILENAME + ".");

        try (FileWriter fileWriter = new FileWriter(FILENAME, true)) {
            fileWriter.write(OUTPUT);
        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        }

        //separating FileWriter and FileReader
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(FILENAME))) {
            System.out.println("Reading from " + FILENAME + ":");
            String s;
            do {
                s = bufferedReader.readLine();
                if (s != null) System.out.println(s);
            } while (s != null);

        } catch (IOException e) {
            System.out.println("Cannot write to file!");
        }
    }

    private static void helpFile() {
        System.out.println("Please input a topic: (if, switch, for, while, do, break, continue)");

        Help help = new Help();
        help.helpOn(takeInput(true));
    }

    private static void scanner() {
        System.out.println("Please input a variable:");

        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            System.out.println("Int detected: " + Integer.toString(scanner.nextInt()));
        } else if (scanner.hasNextDouble()) {
            System.out.println("Double detected: " + Double.toString(scanner.nextDouble()));
        } else if (scanner.hasNextBoolean()) {
            System.out.println("Boolean detected: " + Boolean.toString(scanner.nextBoolean()));
        } else if (scanner.hasNextByte()) {
            System.out.println("Byte detected: " + Byte.toString(scanner.nextByte()));
        } else {
            System.out.println("Unknown input or String.");
        }
    }

    private static void stringToBinary() {
        System.out.println("Please enter a number:");

        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            int i = scanner.nextInt();
            System.out.println("Binary output: " + binaryOutput(i));
        } else if (scanner.hasNextDouble()) {
            double d = scanner.nextDouble();
            System.out.println("Binary output: " + Long.toBinaryString(Double.doubleToRawLongBits(d)));
        }
    }

    private static String binaryOutput(int input) {
        boolean negative = false;
        if (input < 0) {
            negative = true;
            input = -input;
        }

        //compute binary
        ArrayList<Integer> binary = new ArrayList<>();
        while (input > 0) {
            binary.add(input % 2);
            input /= 2;
        }

        //pad out number to 16 bits
        while (binary.size() < 16) binary.add(0);

        //if negative
        if (negative) {
            //flip bits
            for (int i = 0; i < binary.size(); i++) {
                int integer = binary.get(i);

                integer = integer == 0 ? 1 : 0;

                binary.set(i, integer);
            }

            //and add 1
            for (int i = 0; i < binary.size(); i++) {
                int integer = binary.get(i);

                if (integer == 1) {
                    binary.set(i, 0);
                } else {
                    binary.set(i, 1);
                    break;
                }
            }
        }

        //convert ArrayList to inverted string
        StringBuilder sb = new StringBuilder();
        for (int integer = binary.size() - 1; integer >= 0; integer--) {
            sb.append(binary.get(integer));
            if (integer % 4 == 0) sb.append(' ');
        }

        return sb.toString();
    }
}
