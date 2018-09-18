package com.adriantache.stream_practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Help {
    String helpFile = "HELP.TXT";

    Help() {
        //empty constructor to use default file
    }

    Help(String fileName) {
        helpFile = fileName;
    }

    // Display help on a topic
    boolean helpOn(String what) {
        int ch;
        String topic;
        String info;

        // Open the help file.
        try (BufferedReader helpRdr = new BufferedReader(new FileReader(helpFile))) {
            do {
                // read characters until a # is found
                ch = helpRdr.read();
                // now, see if topics match
                if (ch == '#') {
                    topic = helpRdr.readLine();
                    if (what.compareTo(topic) == 0) { // found topic
                        do {
                            info = helpRdr.readLine();

                            if (info != null)
                                System.out.println(info);
                        } while ((info != null) &&
                                (info.compareTo("") != 0));
                        return true;
                    }
                }
            } while (ch != -1);
        } catch (
                IOException exc) {
            System.out.println("Error accessing help file.");
            return false;
        }
        return false; // topic not found
    }
}
