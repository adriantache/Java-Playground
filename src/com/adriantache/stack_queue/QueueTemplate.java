package com.adriantache.stack_queue;

public interface QueueTemplate {
    int ERROR_VALUE = -1;

    int put(int value);

    int get();
}
