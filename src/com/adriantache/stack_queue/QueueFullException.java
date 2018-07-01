package com.adriantache.stack_queue;

class QueueFullException extends Exception {
    QueueFullException(boolean queueOrStack) {
        if (queueOrStack)
            System.out.println("Queue is full!");
        else
            System.out.println("Stack is full!");
    }
}
