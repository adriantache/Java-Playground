package com.adriantache.stack_queue;

class Stack {
    private static final int ERROR_VALUE = -1;
    private int[] stack;
    private int putPosition = 0;

    Stack(int size) {
        stack = new int[size];

        for (int i = 0; i < stack.length; i++) {
            stack[i] = ERROR_VALUE;
        }
    }

    int put(int value) {
        if (putPosition < stack.length) {
            stack[putPosition] = value;
            putPosition++;

            System.out.print(value + " ");

            return putPosition;
        } else {
            System.out.print("Stack is full!");

            return ERROR_VALUE;
        }
    }

    int get() {
        final int GET_POSITION = putPosition - 1;

        if (GET_POSITION >= 0 && stack[GET_POSITION] != ERROR_VALUE) {
            shiftStack();

            System.out.print(stack[GET_POSITION] + " ");

            return stack[GET_POSITION];
        } else {
            System.out.print("Stack is empty!");

            return ERROR_VALUE;
        }
    }

    private void shiftStack() {
        for (int i = putPosition - 1; i > 0; i--) {
            stack[i - 1] = stack[i];
        }

        stack[putPosition - 1] = ERROR_VALUE;

        putPosition--;
    }
}
