package com.adriantache.multithreading;

import com.adriantache.utils.Utils;

import static com.adriantache.utils.Utils.printDescription;
import static com.adriantache.utils.Utils.takeInput;

public class MultiThreading {
    private MultiThreading() {
        throw new IllegalStateException("Utility class");
    }

    public static void main() {
        printDescription("This is a program that is used to play around \n" +
                "with multithreading.");

        System.out.println("Choose an option:\n" +
                "1. Single Thread\n");

        String input = takeInput(false);

        System.out.println();

        //process input
        switch (input) {
            case "1":
                singleThread();
                break;
            default:
                System.out.println("Illegal option!");
        }

        //go to main
        Utils.backToMain();
    }

    private static void singleThread() {
        Thread thread = WasteTimeThread.factoryMethod("Thread 1");

        //one way of checking thread status
//        try {
//            thread.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//            thread.interrupt();
//        }

        while (thread.isAlive()) {
            //do nothing
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(thread.getName() + " has finished.");
    }

}

class WasteTimeThread implements Runnable {
    private Thread thread;

    WasteTimeThread(String name) {
        thread = new Thread(this, name);
    }

    static Thread factoryMethod(String name) {
        WasteTimeThread wasteTimeThread = new WasteTimeThread(name);

        wasteTimeThread.thread.start();

        //this does nothing
        wasteTimeThread.thread.setPriority(Thread.NORM_PRIORITY);

        return wasteTimeThread.thread;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                thread.sleep(400);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
