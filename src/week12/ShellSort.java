/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week12;

/**
 * Shell Sort implementation. Insertion sort that uses Divide and Conquer by
 * splitting the array into several smaller sub-arrays and sorting them,
 * then combining the sub arrays until a final Insertion Sort is performed.
 */
public class ShellSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] table) { // O(n^3/2)
        final double shellConstant = 2.2; // divide the gap by this unless gap == 2
        int gap = table.length / 2; // starting gap is length / 2
        while (gap > 0) {
            // starting at the second value in the first sub-array (at index gap)
            for (int position = gap; position < table.length; ++position) {
                // starting index = gap
                int currentPosition = position; // Pointer to the current location
                T nextVal = table[currentPosition]; // store the current element
                // while there is an index to compare to and the current element is
                // less that the preceding element in the subarray
                while (currentPosition > gap - 1 &&
                        nextVal.compareTo(table[currentPosition - gap]) < 0) {
                    // swap
                    table[currentPosition] = table[currentPosition - gap];
                    // move to the preceding index in the subarray
                    currentPosition -= gap;
                }
                table[currentPosition] = nextVal; // set the final position as the current value
            }
            // reduce gap
            if (gap == 2) { // 2 / 2.2 == 0, so just set gap to 1
                gap = 1;
            } else {
                gap = (int) (gap / shellConstant); // divide by 2.2
            }
        }


    }
}
