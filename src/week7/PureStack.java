/*
 * Course: CSC-1120
 * Stacks and Queues
 * PureStack
 * Name: Sean Jones
 * Last Updated: 10-14-25
 */
package week7;

import java.util.EmptyStackException;

/**
 * A simple Stack interface
 * @param <E> the element type stored in the stack
 */
public interface PureStack<E> {
    /**
     * Tests if this stack is empty.
     * @return true if and only if this stack contains no items; false otherwise.
     */
    boolean empty();

    /**
     * Pushes an item onto the top of this stack.
     * @param item the item to be pushed onto this stack.
     * @return the item argument.
     */
    E push(E item);

    /**
     * Removes the object at the top of this stack and returns that object as the
     * value of this function.
     * @return The object at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    E pop() throws EmptyStackException;

    /**
     * Looks at the object at the top of this stack without removing it from the stack.
     * @return the object at the top of this stack
     * @throws EmptyStackException if this stack is empty
     */
    E peek() throws EmptyStackException;
}
