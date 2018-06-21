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

    //copy a stack
    Stack(Stack stack) {
        int[] temp = stack.getStack();
        this.stack = new int[temp.length];
        System.arraycopy(temp, 0, this.stack, 0, temp.length);

        this.putPosition = stack.getPutPosition();
    }

    private int[] getStack() {
        return stack;
    }

    private int getPutPosition() {
        return putPosition;
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
            putPosition--;

            System.out.print(stack[GET_POSITION] + " ");

            return stack[GET_POSITION];
        } else {
            System.out.print("Stack is empty!");

            return ERROR_VALUE;
        }
    }
}
