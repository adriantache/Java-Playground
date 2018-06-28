package com.adriantache.stack_queue;

import static com.adriantache.utils.Utils.ERROR_VALUE;

class CircularQueue implements QueueTemplate {
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
        int[] temp = queue.getQueue();
        this.queue = new int[temp.length];
        System.arraycopy(temp, 0, this.queue, 0, temp.length);

        this.putPosition = queue.getPutPosition();
    }

    private int[] getQueue() {
        return queue;
    }

    private int getPutPosition() {
        return putPosition;
    }

    public int put(int value) {
        if (putPosition < queue.length) {
            queue[putPosition] = value;
            putPosition++;

            System.out.print(value + " ");
        } else {
            System.out.print(":queue reset: ");

            putPosition = 0;
            put(value);
        }

        return 0;
    }

    public int get() {
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
