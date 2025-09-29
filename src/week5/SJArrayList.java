/*
 * Course: CSC-1120
 * ArrayList
 * SJArrayList
 * Name: Sean Jones
 * Last Updated: 09-29-25
 */
package week5;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * A simple implementation of an ArrayList
 * @param <E> the element stored in the ArrayList
 */
public class SJArrayList<E> implements List<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int size;

    /**
     * No param-constructor that defaults the starting capacity of the
     * backing array to 10
     */
    @SuppressWarnings("unchecked")
    public SJArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor with one parameter that will set the starting
     * capacity of the list
     * @param startingCapacity the starting capacity of the list
     */
    public SJArrayList(int startingCapacity) {
        this.size = 0;
        this.data = (E[]) new Object[startingCapacity];
    }

    @Override
    public int size() { // O(1)
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0; // O(1)
    }

    @Override
    public boolean contains(Object o) { // O(n)
        for(int i = 0; i < this.size; ++i) {
            if(this.data[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        // Homework problem
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // Homework-adjacent problem
        return null;
    }

    @Override
    public boolean add(E e) {
        if(this.size == this.data.length) {

            // we got a problem. Need to make a bigger array
        }
        this.data[size++] = e;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {

    }

    @Override
    public E get(int index) {
        return null;
    }

    @Override
    public E set(int index, E element) {
        return null;
    }

    @Override
    public void add(int index, E element) {

    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        // Homework problem
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        // Homework-adjacent problem
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
