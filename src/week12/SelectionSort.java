/*
 * Course: CSC-1120
 * Sorting
 * Selection Sort
 * Name: Sean Jones
 * Last Updated: 11-17-25
 */
package week12;

/**
 * Selection sort implementation. For each index starting at zero, iterate through the
 * array and find the minimum value. Swap the minimum value with the current index.
 */
public class SelectionSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] table) { // O(n^2)
        // start at beginning (index 0)
        // iterate through array finding the smallest value
        // swap values so the smallest is in the proper index
        for(int i = 0; i < table.length; ++i) { // index we're trying to fill
            int min = i;
            for(int j = i + 1; j < table.length - 1; ++j) { // iterating
                if(table[j].compareTo(table[min]) < 0) {
                    // update min
                    min = j;
                }
            }
            // swap min with current index
            T temp = table[i];
            table[i] = table[min];
            table[min] = temp;
        }
    }
}
