package com.adriantache.stack_queue;

import static com.adriantache.utils.Utils.*;

public class StackQueue {
    public static void main() {
        final int QUEUE_SIZE = 10;
        final String FETCH_MESSAGE = "Then we try to fetch the data from that ";

        printDescription("This is a program that creates a few tools such as a\n" +
                "normal queue, a circular queue and a stack.");

        //initial values
        int[] values = new int[QUEUE_SIZE * 2];
        System.out.print("Initial values: ");
        for (int i = 0; i < values.length; i++) {
            values[i] = i + 1;
            System.out.print(i+1);
            if (i < values.length - 1) System.out.print(',');
        }

        //regular queue
        System.out.println();
        System.out.println();
        System.out.println("First we try to put the data in a regular size " + QUEUE_SIZE + " queue:");
        Queue queue = new Queue(QUEUE_SIZE);
        for (int value2 : values) {
            try {
                queue.put(value2);
            } catch (QueueFullException e) {
                break;
            }
        }
        System.out.println(FETCH_MESSAGE + "queue:");
        do {
            try {
                queue.get();
            } catch (QueueEmptyException e) {
                break;
            }
        } while(true);

        //circular queue
        System.out.println();
        System.out.println("First we try to put the data in a size " + QUEUE_SIZE + " circular queue:");
        CircularQueue circularQueue = new CircularQueue(QUEUE_SIZE);
        for (int value1 : values) {
            circularQueue.put(value1);
        }
        System.out.println();
        System.out.println(FETCH_MESSAGE + "queue:");
        do {
            try {
                circularQueue.get();
            } catch (QueueEmptyException e) {
                break;
            }
        } while(true);

        //growable queue
        System.out.println();
        System.out.println("First we try to put the data in a \"growable\" size " + QUEUE_SIZE + " queue:");
        GrowableQueue growableQueue = new GrowableQueue(QUEUE_SIZE);
        for (int value3 : values) {
            growableQueue.put(value3);
        }
        System.out.println();
        System.out.println(FETCH_MESSAGE + "size " + growableQueue.getQueueSize() + " queue:");
        do {
            try {
                growableQueue.get();
            } catch (QueueEmptyException e) {
                break;
            }
        } while(true);

        //stack
        System.out.println();
        System.out.println("First we try to put the data in a size " + QUEUE_SIZE + " stack:");
        Stack stack = new Stack(QUEUE_SIZE);
        for (int value : values) {
            try {
                stack.put(value);
            } catch (QueueFullException e) {
                break;
            }
        }
        System.out.println(FETCH_MESSAGE + "stack:");
        do {
            try {
                stack.get();
            } catch (QueueEmptyException e) {
                break;
            }
        } while(true);

        System.out.println();

        backToMain();
    }
}
