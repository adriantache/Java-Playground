package com.adriantache.stack_queue;

import static com.adriantache.utils.Utils.ERROR_VALUE;

class GrowableQueue implements QueueTemplate {
    private int[] queue;
    private int putPosition = 0;

    GrowableQueue(int size) {
        queue = new int[size];

        for (int i = 0; i < queue.length; i++) {
            queue[i] = ERROR_VALUE;
        }
    }

    //copy a queue
    GrowableQueue(GrowableQueue queue) {
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

            return putPosition;
        } else {
            //create a queue that is 1 position larger than the existing queue
            int[] temp = new int[putPosition + 1];

            //copy existing queue to new queue
            System.arraycopy(this.queue, 0, temp, 0, this.queue.length);

            //assign by reference the queue to the temporary queue
            this.queue = temp;

            //add the value
            this.queue[putPosition] = value;

            System.out.print(value + " ");

            putPosition++;

            return putPosition;
        }
    }

    public int get() throws QueueEmptyException{
        if (queue[0] != ERROR_VALUE) {
            int temp = queue[0];
            shiftQueue();

            System.out.print(temp + " ");

            return temp;
        } else {
           throw new QueueEmptyException(true);
        }
    }

    private void shiftQueue() {
        System.arraycopy(queue, 1, queue, 0, queue.length - 1);

        queue[queue.length - 1] = ERROR_VALUE;

        putPosition--;
    }

    int getQueueSize(){
        return queue.length;
    }
}
