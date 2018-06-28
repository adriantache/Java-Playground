package com.adriantache.sorting_algorithms;

import java.util.Arrays;
import java.util.Random;

import static com.adriantache.utils.Utils.backToMain;

public class SortingAlgorithms {
    public static void main() {
        System.out.println("This is a program that uses a number of ");
        System.out.println("sorting algorithms to sort a random array.");
        System.out.println();

        int[] values = createRandomArray();

        //bubble sort
        System.out.println("Initial array:");
        System.out.println(Arrays.toString(values));
        values = bubbleSort(values);
        System.out.println("Array sorted with BubbleSort:");
        System.out.println(Arrays.toString(values));
        checkArraySorted(values);

        //arrays.sort
        System.out.println();
        values = createRandomArray();
        System.out.println("Initial array:");
        System.out.println(Arrays.toString(values));
        Arrays.sort(values);
        System.out.println("Array sorted with Arrays.sort:");
        System.out.println(Arrays.toString(values));
        checkArraySorted(values);

        //arrays.parallelSort
        System.out.println();
        values = createRandomArray();
        System.out.println("Initial array:");
        System.out.println(Arrays.toString(values));
        Arrays.parallelSort(values);
        System.out.println("Array sorted with Arrays.sort:");
        System.out.println(Arrays.toString(values));
        checkArraySorted(values);

        //quicksort
        System.out.println();
        values = createRandomArray();
        System.out.println("Initial array:");
        System.out.println(Arrays.toString(values));
        quickSort(values, 0, values.length - 1);
        System.out.println("Array sorted with QuickSort:");
        System.out.println(Arrays.toString(values));
        checkArraySorted(values);

        backToMain();
    }

    private static int[] createRandomArray() {
        final int ARRAY_SIZE = 25;
        int[] values = new int[ARRAY_SIZE];

        Random random = new Random();
        for (int i = 0; i < values.length; i++) {
            values[i] = random.nextInt(100);
        }

        return values;
    }

    private static void checkArraySorted(int[] values) {
        for (int i = 0; i < values.length-1; i++) {
            if (values[i]>values[i+1]) {
                System.out.println("Array is NOT sorted. [!!!]");
                return;
            }
        }

        System.out.println("Array is sorted.");
    }

    private static void quickSort(int[] values, int low, int high) {
        if (low >= high) return;

        int pivotLocation = quickSortPivotLocation(values, low, high);

        quickSort(values, low, pivotLocation - 1);
        quickSort(values, pivotLocation + 1, high);
    }

    private static int quickSortPivotLocation(int[] values, int low, int high) {
        int pivot = values[high];
        int pivotLocation = low;

        //move all elements lower than pivot to before pivotLocation
        for (int i = low; i < high; i++) {
            if (values[i] <= pivot) {
                int temp = values[pivotLocation];
                values[pivotLocation] = values[i];
                values[i] = temp;

                pivotLocation++;
            }
        }

        //swap pivot itself
        values[high] = values[pivotLocation];
        values[pivotLocation] = pivot;

        return pivotLocation;
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
