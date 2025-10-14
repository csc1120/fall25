/*
 * Course: CSC-1120
 * Stacks and Queues
 * SJQueue
 * Name: Sean Jones
 * Last Updated: 10-14-25
 */
package week7;

import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * An implementation of the PureStack interface
 * @param <E> the type of element stored in the stack
 */
public class SJStack<E> implements PureStack<E> {
    private final LinkedList<E> items;

    /**
     * No-param constructor for the stack
     */
    public SJStack() {
        this.items = new LinkedList<>();
    }

    @Override
    public boolean empty() { // O(1)
        return items.isEmpty();
    }

    @Override
    public E push(E e) { // O(1)
        this.items.add(e);
        return e;
    }

    @Override
    public E pop() throws EmptyStackException { // O(1)
        if(items.isEmpty()) {
            throw new EmptyStackException();
        }
        return items.removeLast();
    }

    @Override
    public E peek() throws EmptyStackException { // O(1)
        if(items.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.items.get(items.size() - 1);
    }
}
