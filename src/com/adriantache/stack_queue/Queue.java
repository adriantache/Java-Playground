package com.adriantache.stack_queue;

class Queue {
    private static final int ERROR_VALUE = -1;
    private int[] queue;
    private int putPosition = 0;

    Queue(int size) {
        queue = new int[size];

        for (int i = 0; i < queue.length; i++) {
            queue[i] = ERROR_VALUE;
        }
    }

    int put(int value) {
        if (putPosition < queue.length) {
            queue[putPosition] = value;
            putPosition++;

            System.out.print(value + " ");

            return putPosition;
        } else {
            System.out.print("Queue is full!");

            return ERROR_VALUE;
        }
    }

    int get() {
        if (queue[0] != ERROR_VALUE) {
            int temp = queue[0];
            shiftQueue();

            System.out.print(temp + " ");

            return temp;
        } else {
            System.out.print("Queue is empty!");

            return ERROR_VALUE;
        }
    }

    private void shiftQueue() {
        for (int i = 0; i < queue.length - 1; i++) {
            queue[i] = queue[i + 1];
        }

        queue[queue.length - 1] = ERROR_VALUE;

        putPosition--;
    }
}
