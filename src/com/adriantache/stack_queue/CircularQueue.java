package com.adriantache.stack_queue;

class CircularQueue {
    private static final int ERROR_VALUE = -1;
    private int[] queue;
    private int putPosition = 0;

    CircularQueue(int size) {
        queue = new int[size];

        for (int i = 0; i < queue.length; i++) {
            queue[i] = ERROR_VALUE;
        }
    }

    //copy a queue
    CircularQueue(CircularQueue queue) {
        this.queue = queue.getQueue();
        this.putPosition = queue.getPutPosition();
    }

    private int[] getQueue() {
        return queue;
    }

    private int getPutPosition() {
        return putPosition;
    }

    void put(int value) {
        if (putPosition < queue.length) {
            queue[putPosition] = value;
            putPosition++;

            System.out.print(value + " ");
        } else {
            System.out.print(":queue reset: ");

            putPosition = 0;
            put(value);
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
        System.arraycopy(queue, 1, queue, 0, queue.length - 1);

        queue[queue.length - 1] = ERROR_VALUE;

        putPosition--;
    }
}
