/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week7;

import java.util.EmptyStackException;
import java.util.LinkedList;

public class SJStack<E> implements PureStack<E> {
    private LinkedList<E> items;

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
        return items.remove(items.size() - 1);
    }

    @Override
    public E peek() throws EmptyStackException { // O(1)
        if(items.isEmpty()) {
            throw new EmptyStackException();
        }
        return this.items.get(items.size() - 1);
    }
}
