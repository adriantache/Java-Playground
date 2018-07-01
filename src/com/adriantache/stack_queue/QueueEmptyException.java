package com.adriantache.stack_queue;

class QueueEmptyException extends Exception {
    QueueEmptyException(boolean queueOrStack) {
        if (queueOrStack)
            System.out.println("Queue is empty!");
        else
            System.out.println("Stack is empty!");
    }
}
