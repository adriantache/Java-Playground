package com.adriantache.stack_queue;

public class StackQueue {

    public static void main() {
        final int ERROR_VALUE = -1;
        final int QUEUE_SIZE = 10;

        System.out.println("This is a program that creates a few tools such as a");
        System.out.println("normal queue, a circular queue and a stack.");
        System.out.println();

        //initial values
        int[] values = new int[QUEUE_SIZE * 2];
        System.out.print("Initial values: ");
        for (int i = 0; i < values.length; i++) {
            values[i] = i + 1;
            System.out.print(i);
            if (i < values.length - 1) System.out.print(',');
        }

        //regular queue
        System.out.println();
        System.out.println();
        System.out.println("First we try to put the data in a regular size 10 queue:");
        Queue queue = new Queue(QUEUE_SIZE);
        for (int i = 0; i < values.length; i++) {
            if (queue.put(values[i]) == ERROR_VALUE) break;
        }
        System.out.println();
        System.out.println("Then we try to fetch the data from that queue:");
        for (int i = 0; i < values.length; i++) {
            if (queue.get() == ERROR_VALUE) break;
        }

        //circular queue
        System.out.println();
        System.out.println();
        System.out.println("First we try to put the data in a size 10 circular queue:");
        CircularQueue circularQueue = new CircularQueue(QUEUE_SIZE);
        for (int i = 0; i < values.length; i++) {
            circularQueue.put(values[i]);
        }
        System.out.println();
        System.out.println("Then we try to fetch the data from that queue:");
        for (int i = 0; i < values.length; i++) {
            if (circularQueue.get() == ERROR_VALUE) break;
        }

        //stack
        System.out.println();
        System.out.println();
        System.out.println("First we try to put the data in a size 10 stack:");
        Stack stack = new Stack(QUEUE_SIZE);
        for (int i = 0; i < values.length; i++) {
            if (stack.put(values[i]) == ERROR_VALUE) break;
        }
        System.out.println();
        System.out.println("Then we try to fetch the data from that stack:");
        int temp;
        do {
            temp = stack.get();
        } while (temp != ERROR_VALUE);

        System.out.println();
    }
}
