package com.adriantache.stack_queue;

public interface QueueTemplate {
    int put(int value) throws QueueFullException;

    int get() throws QueueEmptyException;
}
