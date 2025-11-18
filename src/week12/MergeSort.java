/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week12;

public class MergeSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] table) {
        if(table.length > 1) {
            // split the array
            int half = table.length / 2;
            T[] left = (T[]) new Comparable[half];
            T[] right = (T[]) new Comparable[table.length - half];
            // copy values into new smaller arrays
            System.arraycopy(table,0, left,0, half);
            System.arraycopy(table, half, right, 0, right.length);
            // recursive
            sort(left);
            sort(right);
            merge(table, left, right);
        }
    }

    public static <T extends Comparable<T>> void merge(T[] output, T[] left, T[] right) {
        int leftPointer = 0;
        int rightPointer = 0;
        int outputPointer = 0;
        while(leftPointer < left.length && rightPointer < right.length) {
            if(left[leftPointer].compareTo(right[rightPointer]) < 0) {
                output[outputPointer++] = left[leftPointer++];
            } else {
                output[outputPointer++] = right[rightPointer++];
            }
        }
        // one array is empty
        while(leftPointer < left.length) {
            output[outputPointer++] = left[leftPointer++];
        }
        while(rightPointer < right.length) {
            output[outputPointer++] = right[rightPointer++];
        }
    }
}
