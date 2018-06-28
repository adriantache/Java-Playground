package com.adriantache.stack_queue;

import static com.adriantache.utils.Utils.ERROR_VALUE;
import static com.adriantache.utils.Utils.backToMain;
import static com.adriantache.utils.Utils.printDescription;

public class StackQueue {

    public static void main() {
        final int QUEUE_SIZE = 10;
        int temp;
        final String FETCH_MESSAGE = "Then we try to fetch the data from that ";

        printDescription("This is a program that creates a few tools such as a\n" +
                "normal queue, a circular queue and a stack.");

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
        System.out.println("First we try to put the data in a regular size " + QUEUE_SIZE + " queue:");
        Queue queue = new Queue(QUEUE_SIZE);
        for (int value2 : values) {
            if (queue.put(value2) == ERROR_VALUE) break;
        }
        System.out.println();
        System.out.println(FETCH_MESSAGE + "queue:");
        do {
            temp = queue.get();
        } while (temp != ERROR_VALUE);

        //circular queue
        System.out.println();
        System.out.println();
        System.out.println("First we try to put the data in a size " + QUEUE_SIZE + " circular queue:");
        CircularQueue circularQueue = new CircularQueue(QUEUE_SIZE);
        for (int value1 : values) {
            circularQueue.put(value1);
        }
        System.out.println();
        System.out.println(FETCH_MESSAGE + "queue:");
        do {
            temp = circularQueue.get();
        } while (temp != ERROR_VALUE);

        //growable queue
        System.out.println();
        System.out.println();
        System.out.println("First we try to put the data in a \"growable\" size " + QUEUE_SIZE + " queue:");
        GrowableQueue growableQueue = new GrowableQueue(QUEUE_SIZE);
        for (int value3 : values) {
            growableQueue.put(value3);
        }
        System.out.println();
        System.out.println(FETCH_MESSAGE + "size " + growableQueue.getQueueSize() + " queue:");
        do {
            temp = growableQueue.get();
        } while (temp != ERROR_VALUE);

        //stack
        System.out.println();
        System.out.println();
        System.out.println("First we try to put the data in a size " + QUEUE_SIZE + " stack:");
        Stack stack = new Stack(QUEUE_SIZE);
        for (int value : values) {
            if (stack.put(value) == ERROR_VALUE) break;
        }
        System.out.println();
        System.out.println(FETCH_MESSAGE + "stack:");
        do {
            temp = stack.get();
        } while (temp != ERROR_VALUE);

        System.out.println();

        backToMain();
    }
}
