/*
 * Course: CSC-1120
 * Stacks and Queues
 * PureQueue
 * Name: Sean Jones
 * Last Updated: 10-14-25
 */
package week7;

import java.util.NoSuchElementException;

/**
 * Simple PureQueue
 * @param <E> the element stored in the queue
 */
public interface PureQueue<E> {
    /**
     * Inserts the specified element into this queue if it is possible to do so
     * immediately without violating capacity restrictions, returning true upon
     * success and throwing an IllegalStateException if no space is currently available.
     * @param e the element to add
     * @return true
     */
    boolean add(E e);

    /**
     * Inserts the specified element into this queue if it is possible to do so immediately
     * without violating capacity restrictions. When using a capacity-restricted queue, this
     * method is generally preferable to add(E), which can fail to insert an element only by
     * throwing an exception.
     * @param e the element to add
     * @return true if the element was added to the queue, false otherwise
     */
    boolean offer(E e);

    /**
     * Retrieves and removes the head of this queue. This method differs from
     * {@link #poll()} only in that it throws an exception if this queue is empty.
     * @return the head of the queue
     * @throws NoSuchElementException if this queue is empty
     */
    E remove() throws NoSuchElementException;

    /**
     * Retrieves and removes the head of this queue, or returns null if this queue is empty.
     * @return the head of this queue, or null if this queue is empty
     */
    E poll();

    /**
     * Retrieves, but does not remove, the head of this queue. This method differs from
     * {link #peek()} only in that it throws an exception if this queue is empty.
     * @return the head of this queue
     * @throws NoSuchElementException if this queue is empty
     */
    E element() throws NoSuchElementException;

    /**
     * Retrieves, but does not remove, the head of this queue, or returns null if
     * this queue is empty.
     * @return the head of this queue, or null if this queue is empty
     */
    E peek();

    /**
     * Returns true is this queue is empty
     * @return true is this queue is empty
     */
    boolean empty();
}
