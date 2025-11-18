/*
 * Course: CSC-1120
 * Sorting
 * Merge Sort
 * Name: Sean Jones
 * Last Updated:
 */
package week12;

/**
 * Merge Sort implementation. Divide the array in half recursively until
 * the array is size 1, then merge it with the other subarrays recursively.
 */
public class MergeSort implements SortAlgorithm {
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> void sort(T[] table) {
        if(table.length > 1) {
            // split the array
            int half = table.length / 2;
            T[] left = (T[]) new Comparable[half];
            T[] right = (T[]) new Comparable[table.length - half];
            // copy values into new smaller arrays
            System.arraycopy(table, 0, left, 0, half);
            System.arraycopy(table, half, right, 0, right.length);
            // recursive
            sort(left);
            sort(right);
            merge(table, left, right);
        }
    }

    /**
     * Merges two arrays into a single, sorted array
     * @param output the resulting sorted array
     * @param left the left subarray
     * @param right the right subarray
     * @param <T> the element type stored in the arrays
     */
    public static <T extends Comparable<T>> void merge(T[] output, T[] left, T[] right) {
        // start all arrays at index 0
        int leftPointer = 0;
        int rightPointer = 0;
        int outputPointer = 0;
        // While both subarrays have elements
        while(leftPointer < left.length && rightPointer < right.length) {
            // choose which element is smaller and add that to the output array
            if(left[leftPointer].compareTo(right[rightPointer]) < 0) {
                output[outputPointer++] = left[leftPointer++];
            } else {
                output[outputPointer++] = right[rightPointer++];
            }
        }
        // one array is empty, copy the rest of the remaining array to the output
        while(leftPointer < left.length) {
            output[outputPointer++] = left[leftPointer++];
        }
        while(rightPointer < right.length) {
            output[outputPointer++] = right[rightPointer++];
        }
    }
}
