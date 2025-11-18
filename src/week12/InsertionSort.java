/*
 * Course: CSC-1120
 * Sorting
 * Insertion Sort
 * Name: Sean Jones
 * Last Updated: 11-17-25
 */
package week12;

/**
 * Insertion sort implementation. Start at index 1. If the preceding element is
 * less than the current element, swap them and check again until it is not less
 * or until we are at index 0.
 */
public class InsertionSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] table) { // O(n^2)
        for(int i = 1; i < table.length; ++i) {
            T current = table[i]; // current value
            int location = i;
            while(location > 0 && current.compareTo(table[location - 1]) < 0) {
                table[location] = table[location - 1]; // swap with the preceding location
                --location;
            }
            table[location] = current; // place the current value
        }
    }
}
