/*
 * Course: CSC-1120
 * Generics
 * GenericList
 * Name: Sean Jones
 * Last Updated: 09-26-25
 */
package week4;

import java.util.ArrayList;

/**
 * Example of a different generic "type"
 * @param <T> The Type of element in the List
 */
public class GenericList<T> {
    private ArrayList<T> list;

    /**
     * Adds an element to the list
     * @param t the element to add to the list
     * @return true if the element was added, false otherwise
     */
    public boolean add(T t) {
        return list.add(t);
    }

    /**
     * Gets an element from the given index
     * @param i the index to get the element from
     * @return the element at index i
     */
    public T get(int i) {
        return list.get(i);
    }
}
