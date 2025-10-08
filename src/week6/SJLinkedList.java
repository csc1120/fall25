/*
 * Course: CSC-1120
 * Linked List
 * SJLinkedList
 * Name: Sean Jones
 * Last Updated:
 */
package week6;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Simple Linked List implementation
 * @param <E> the element stored in the list
 */
public class SJLinkedList<E> implements List<E> {
    private static class Node<E> {
        private E data;
        private Node<E> next;

        private Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "data: " + data;
        }
    }

    private class Iter implements java.util.Iterator<E> {
        private Node<E> next;
        private Node<E> lastReturned;
        private Node<E> previous;
        private Node<E> lastReturnedPrevious;

        private Iter() {
            this.next = SJLinkedList.this.head;
            this.lastReturned = null;
            this.lastReturnedPrevious = null;
            this.previous = null;
        }

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return this.next != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public E next() {
            if(this.next == null) {
                throw new NoSuchElementException();
            }
            this.lastReturnedPrevious = this.previous;
            this.previous = this.next;
            this.lastReturned = this.next;
            this.next = this.next.next;
            return this.lastReturned.data;
        }

        /**
         * Removes from the underlying collection the last element returned
         * by this iterator (optional operation).  This method can be called
         * only once per call to {@link #next}.
         * <p>
         * The behavior of an iterator is unspecified if the underlying collection
         * is modified while the iteration is in progress in any way other than by
         * calling this method, unless an overriding class has specified a
         * concurrent modification policy.
         * <p>
         * The behavior of an iterator is unspecified if this method is called
         * after a call to the {@link #forEachRemaining forEachRemaining} method.
         *
         * @throws UnsupportedOperationException if the {@code remove}
         *                                       operation is not supported by this iterator
         * @throws IllegalStateException         if the {@code next} method has not
         *                                       yet been called, or the {@code remove} method has already
         *                                       been called after the last call to the {@code next}
         *                                       method
         * @implSpec The default implementation throws an instance of
         * {@link UnsupportedOperationException} and performs no other action.
         */
        @Override
        public void remove() {
            if(this.lastReturned == null) {
                throw new IllegalStateException();
            }
            if(this.lastReturnedPrevious == null) {
                SJLinkedList.this.head = this.next;
            } else {
                this.lastReturnedPrevious.next = this.next;
            }
            this.previous = this.lastReturnedPrevious;
            this.lastReturned = null;
            --SJLinkedList.this.size;
        }

        /**
         * Performs the given action for each remaining element until all elements
         * have been processed or the action throws an exception.  Actions are
         * performed in the order of iteration, if that order is specified.
         * Exceptions thrown by the action are relayed to the caller.
         * <p>
         * The behavior of an iterator is unspecified if the action modifies the
         * collection in any way (even by calling the {@link #remove remove} method
         * or other mutator methods of {@code Iterator} subtypes),
         * unless an overriding class has specified a concurrent modification policy.
         * <p>
         * Subsequent behavior of an iterator is unspecified if the action throws an
         * exception.
         *
         * @param action The action to be performed for each element
         * @throws NullPointerException if the specified action is null
         * @implSpec <p>The default implementation behaves as if:
         * <pre>{@code
         *     while (hasNext())
         *         action.accept(next());
         * }</pre>
         * @since 1.8
         */
        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            while(this.hasNext()) {
                action.accept(this.next());
            }
        }
    }

    private Node<E> head;
    private int size;

    /**
     * Constructor for the list
     */
    public SJLinkedList() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public int size() { // O(1)
        return this.size;
    }

    @Override
    public boolean isEmpty() { // O(1)
        return this.size == 0;
        // or head == null
    }

    @Override
    public boolean contains(Object o) { // O(n)
        Node<E> current = head;
        for(int i = 0; i < this.size; ++i) {
            if(current != null) {
                if(current.data.equals(o)) {
                    return true;
                }
            }
            current = current.next;
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iter();
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }

    @Override
    public boolean add(E e) { // O(n)
        // make new node
        // put data in node
        // add node to list
        // increment the size
        // case 1 - head is null, nothing in the list
        Node<E> temp = new Node<>(e, null);
        if(head == null) {
            this.head = temp;
        } else {
            Node<E> current = this.head;
            while(current.next != null) {
                current = current.next;
            }
            current.next = temp;
        }
        ++this.size;
        return true;
    }

    @Override
    public boolean remove(Object o) { // O(n)
        if(this.head != null) {
            Node<E> current = this.head;
            while(current.next != null && !current.next.data.equals(o)) {
                current = current.next;
            }
            if(current.next != null) {
                // remove it
                current.next = current.next.next;
                --this.size;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() { // O(1)
        this.head = null;
        this.size = 0;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException { // O(n)
        validateIndex(index);
        Node<E> current = this.head;
        for(int i = 0; i < index; ++i) {
            current = current.next;
        }
        return current.data;
    }

    private void validateIndex(int index) throws IndexOutOfBoundsException {
        if(index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E set(int index, E element) {
        validateIndex(index);
        Node<E> current = this.head;
        for(int i = 0; i < index; ++i) {
            current = current.next;
        }
        E old = current.data;
        current.data = element;
        return old;
    }

    @Override
    public void add(int index, E element) { // O(n)
        if(index != this.size) {
            validateIndex(index);
        }
        if(index == 0) {
            this.head = new Node<>(element, this.head);
        }
        Node<E> current = this.head;
        for(int i = 0; i < index - 1; ++i) {
            current = current.next;
        }
        current.next = new Node<>(element, current.next);
        ++this.size;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator<E> listIterator() {
        return null;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return List.of();
    }
}
