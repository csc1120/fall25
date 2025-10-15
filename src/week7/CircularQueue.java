/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week7;

import java.util.NoSuchElementException;

/**
 * A PureQueue that uses a backing array as the backing structure
 * @param <E> the element type stored in the queue
 */
public class CircularQueue<E> implements PureQueue<E> {
    private final E[] queue;
    private int front;
    private int back;
    private int size;

    /**
     * No-param constructor
     */
    @SuppressWarnings("unchecked")
    public CircularQueue() {
        final int defaultCapacity = 10;
        this.queue = (E[]) new Object[defaultCapacity];
        front = 0;
        back = 0;
        size = 0;
    }

    @Override
    public boolean add(E e) {
        if(this.size == this.queue.length) {
            throw new IllegalStateException();
        }
        return offer(e);
    }

    @Override
    public boolean offer(E e) {
        if(this.size == this.queue.length) {
            return false;
        }
        this.queue[back++] = e; // add at the back
        back %= this.queue.length;
        ++size;
        return true;
    }

    @Override
    public E remove() throws NoSuchElementException {
        if(empty()) {
            throw new NoSuchElementException();
        }
        return poll();
    }

    @Override
    public E poll() {
        if(empty()) {
            return null;
        }
        E result = this.queue[front++]; // remove from the front
        front %= this.queue.length;
        --this.size;
        return result;
    }

    @Override
    public E element() throws NoSuchElementException {
        if(empty()) {
            throw new NoSuchElementException();
        }
        return peek();
    }

    @Override
    public E peek() {
        if(empty()) {
            return null;
        }
        return this.queue[front];
    }

    @Override
    public boolean empty() {
        return this.size == 0;
    }
}
