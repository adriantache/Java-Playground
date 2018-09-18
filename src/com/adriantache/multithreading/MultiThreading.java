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
                "1. Single Thread\n" +
                "2. Synchronized Threads\n");

        String input = takeInput(false);

        System.out.println();

        //process input
        switch (input) {
            case "1":
                singleThread();
                break;
            case "2":
                synchronize();
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
                Thread.currentThread().interrupt();
            }
        }

        System.out.println(thread.getName() + " has finished.");
    }


    private static void synchronize() {
        TickTock tt = new TickTock();
        MyThread mtl = MyThread.createAndStart("Tick", tt);
        MyThread mt2 = MyThread.createAndStart("Tock", tt);
        try {
            mtl.thread.join();
            mt2.thread.join();
        } catch (InterruptedException exc) {
            System.out.println("Main thread interrupted.");
            Thread.currentThread().interrupt();
        }
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
                Thread.sleep(400);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}

// Use wait() and notify() to create a ticking clock,
class TickTock {
    String state; // contains the state of the clock

    synchronized void tick(boolean running) {
        if (!running) {
            // stop the clock
            state = "ticked";
            // notify any waiting threads
            notify();
            return;
        }

        System.out.print("Tick ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        state = "ticked"; // set the current state to ticked
        notify(); // let tock() run

        try {
            while (!state.equals("tocked"))
                wait(); // wait for tock() to complete
        } catch (InterruptedException exc) {
            System.out.println("Thread interrupted.");
            Thread.currentThread().interrupt();
        }
    }

    synchronized void tock(boolean running) {
        if (!running) {
            // stop the clock
            state = "tocked";
            // notify any waiting threads
            notify();
            return;
        }

        System.out.println("Tock ");
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
        state = "tocked";
        notify();

        try {
            while (!state.equals("ticked"))
                wait();
        } catch (InterruptedException exc) {
            System.out.println("Thread interrupted.");
            Thread.currentThread().interrupt();
        }
    }
}

class MyThread implements Runnable {
    Thread thread;
    TickTock ttOb;

    // Construct a new thread
    MyThread(String name, TickTock tt) {
        thread = new Thread(this, name);
        ttOb = tt;
    }

    // A factory method that creates and starts a thread
    public static MyThread createAndStart(String name, TickTock tt) {
        MyThread myThread = new MyThread(name, tt);
        myThread.thread.start(); // start the thread
        return myThread;
    }

    // Entry point of thread
    public void run() {
        if (thread.getName().compareTo("Tick") == 0) {
            for (int i = 0; i < 5; i++) ttOb.tick(true);
            ttOb.tick(false);
        } else {
            for (int i = 0; i < 5; i++) ttOb.tock(true);
            ttOb.tock(false);
        }
    }
}