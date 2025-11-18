/*
 * Course: CSC-1020
 * Sorting
 * SortAlgorithm
 * Name: Sean Jones
 * Last Updated: 11-17-25
 */
package week12;

/**
 * An interface for all sorting algorithms
 */
public interface SortAlgorithm {
    /**
     * A method that sorts a given array
     * @param table the array to sort
     * @param <T> the element type stored in the array
     */
    <T extends Comparable<T>> void sort(T[] table);
}
