package com.adriantache.sorting_algorithms;

import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms {
    public static void main() {
        System.out.println("This is a program that uses a number of ");
        System.out.println("sorting algorithms to sort a random array.");
        System.out.println();

        int[] values = createRandomArray();

        System.out.println("Initial array:");
        System.out.println(Arrays.toString(values));
        values = bubbleSort(values);
        System.out.println("Array sorted with BubbleSort:");
        System.out.println(Arrays.toString(values));

        System.out.println();
        values = createRandomArray();
        System.out.println("Initial array:");
        System.out.println(Arrays.toString(values));
        Arrays.sort(values);
        System.out.println("Array sorted with Arrays.sort:");
        System.out.println(Arrays.toString(values));

        System.out.println();
        values = createRandomArray();
        System.out.println("Initial array:");
        System.out.println(Arrays.toString(values));
        values = quickSort(values);
        System.out.println("Array sorted with QuickSort:");
        System.out.println(Arrays.toString(values));
    }

    private static int[] quickSort(int[] values) {
        

        return values;
    }

    private static int[] createRandomArray() {
        final int ARRAY_SIZE = 20;
        int[] values = new int[ARRAY_SIZE];

        Random random = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(100);
        }

        return values;
    }

    private static int[] bubbleSort(int[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            for (int j = i + 1; j < values.length; j++) {
                if (values[i] > values[j]) {
                    int temp = values[j];
                    values[j] = values[i];
                    values[i] = temp;
                }
            }
        }

        return values;
    }
}
