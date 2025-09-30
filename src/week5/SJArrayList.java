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
    public SJArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor with one parameter that will set the starting
     * capacity of the list
     * @param startingCapacity the starting capacity of the list
     */
    @SuppressWarnings("unchecked")
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Object[] toArray() {
        // Homework problem
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        // Homework-adjacent problem
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E e) { // O(1)
        if(this.size == this.data.length) {
            reallocate(); // make the backing array bigger
        }
        this.data[size++] = e; // increment size after adding
        return true;
    }

    @SuppressWarnings("unchecked")
    private void reallocate() { // O(n)
        // make a bigger array
        E[] temp = (E[]) new Object[this.data.length * 2 + 1];
        System.arraycopy(this.data, 0, temp, 0, this.data.length);
        this.data = temp;
    }

    @Override
    public boolean remove(Object o) { // O(n)
        // find it
        for(int i = 0; i < this.size; ++i) {
            if(this.data[i].equals(o)) {
                // remove it
                for(int j = i; j < this.size - 1; ++j) {
                    this.data[j] = this.data[j + 1]; // shift all later indexes left
                }
                --this.size; // decrement size
                return true;
            }
        }
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
    @SuppressWarnings("unchecked")
    public void clear() { // O(1)
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException { // O(1)
        validateIndex(index); // validate the index
        return this.data[index]; // return the element at the index
    }

    private void validateIndex(int index) throws IndexOutOfBoundsException {
        if(index >= this.size || index < 0) { // O(1)
            throw new IndexOutOfBoundsException("Index " + index + " is invalid for" +
                    " a list of size " + this.size);
        }
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException { // O(1)
        validateIndex(index); // validate the index
        E result = this.data[index]; // store the old element
        this.data[index] = element; // set the new element
        return result; // return the old element
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        // what is the Big-O?
        validateIndex(index); // validate the index
        if(this.size == this.data.length) { // make sure we have room
            reallocate();
        }
        // shift all later elements to the right to make room for the new element
        for(int i = this.size - 1; i >= index; --i) {
            this.data[i + 1] = this.data[i];
        }
        this.data[index] = element; // add the element at the index
        ++this.size; // increment size
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        // what is the Big-O?
        validateIndex(index); // validate the index
        E result = this.data[index];
        // shift all the later elements left to close the gap
        for(int i = index; i < this.size - 1; ++i) {
            this.data[i] = this.data[i + 1];
        }
        --this.size; // decrement size
        return result; // return the removed element
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
