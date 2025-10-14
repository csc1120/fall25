/*
 * Course: CSC-1120
 * Stacks and Queues
 * SJQueue
 * Name: Sean Jones
 * Last Updated: 10-14-25
 */
package week7;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * A simple implementation of the PureQueue interface
 * @param <E> the type of element stored in the queue
 */
public class SJQueue<E> implements PureQueue<E> {
    private final LinkedList<E> items;

    /**
     * No-param constructor that initialized the backing list
     */
    public SJQueue() {
        this.items = new LinkedList<>();
    }

    @Override
    public boolean add(E e) throws IllegalArgumentException { // O(1)
        if(e == null) {
            throw new IllegalArgumentException();
        }
        return items.add(e);
    }

    @Override
    public boolean offer(E e) { // O(1)
        if(e == null) {
            return false;
        }
        return items.add(e);
    }

    @Override
    public E remove() throws NoSuchElementException { // O(1)
        if(items.isEmpty()) {
            throw new NoSuchElementException();
        }
        return items.removeFirst();
    }

    @Override
    public E poll() { // O(1)
        if(items.isEmpty()) {
            return null;
        }
        return items.removeFirst();
    }

    @Override
    public E element() throws NoSuchElementException { // O(1)
        if(items.isEmpty()) {
            throw new NoSuchElementException();
        }
        return items.getFirst();
    }

    @Override
    public E peek() { // O(1)
        if(items.isEmpty()) {
            return null;
        }
        return items.getFirst();
    }

    @Override
    public boolean empty() {
        return items.isEmpty();
    }
}
