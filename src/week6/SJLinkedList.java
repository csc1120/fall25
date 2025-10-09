/*
 * Course: CSC-1120
 * Linked List
 * SJLinkedList
 * Name: Sean Jones
 * Last Updated: 10-09-25
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
 *
 * @param <E> the element stored in the list
 */
@SuppressWarnings("unused")
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

    private class Iter implements Iterator<E> {
        private Node<E> next;
        private Node<E> previous;
        private Node<E> lastReturned;
        private Node<E> lastReturnedPrevious;

        private Iter() {
            this.next = SJLinkedList.this.head;
            this.previous = null;
            this.lastReturned = null;
            this.lastReturnedPrevious = null;
        }

        @Override
        public boolean hasNext() {
            return this.next != null;
        }

        @Override
        public E next() {
            if (this.next == null) {
                throw new NoSuchElementException();
            }
            // Update all the instance variables
            this.lastReturnedPrevious = this.previous;
            this.lastReturned = this.next;
            this.previous = this.next;
            this.next = this.next.next;
            return lastReturned.data;
        }

        @Override
        public void remove() {
            if (this.lastReturned == null) {
                throw new IllegalStateException();
            }
            if (this.lastReturnedPrevious == null) { //removing the head
                SJLinkedList.this.head = this.next;
            } else {
                this.lastReturnedPrevious.next = this.next;
            }
            // Update instance variables
            this.previous = this.lastReturnedPrevious;
            // lastReturned is now deleted
            this.lastReturned = null;
            // since lastReturned is null, this reference also does not exist
            this.lastReturnedPrevious = null;
            // Update the LinkedList's size
            --SJLinkedList.this.size;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            while (this.hasNext()) {
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
        for (int i = 0; i < this.size; ++i) {
            if (current != null) {
                if (current.data.equals(o)) {
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
        Object[] result = new Object[this.size];
        Iterator<E> it = this.iterator();
        for (int i = 0; i < this.size; ++i) { // O(n)
            result[i] = it.next();
        }
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < this.size) {
            a = (T[]) new Object[this.size];
        }
        Iterator<E> it = this.iterator();
        for (int i = 0; i < this.size; ++i) { // O(n)
            a[i] = (T) it.next();
        }
        if (a.length > this.size) {
            a[this.size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) { // O(n)
        Node<E> temp = new Node<>(e, null);
        if (head == null) { // empty list
            this.head = temp;
        } else { // add to the end of the list
            Node<E> current = getNode(this.size - 1);
            current.next = temp;
        }
        ++this.size;
        return true;
    }

    @Override
    public boolean remove(Object o) { // O(n)
        if (this.head != null) {
            Node<E> current = this.head;
            while (current.next != null && !current.next.data.equals(o)) {
                current = current.next;
            }
            if (current.next != null) {
                // remove it
                current.next = current.next.next;
                --this.size;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) { // O(mn) = O(n)
        for (Object o : c) { // O(m) - size of c not related to the List
            if (!this.contains(o)) { // O(n)
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        Node<E> tail = getNode(this.size - 1); // O(n)
        for (E e : c) { // O(m)
            tail.next = new Node<>(e, null);
            tail = tail.next;
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) throws IndexOutOfBoundsException {
        if (index != this.size) {
            validateIndex(index);
        }
        Node<E> current = index == 0 ? this.head : getNode(index - 1); // O(n)
        for (E e : c) { // O(m)
            if(index == 0) {
                this.head = new Node<>(e, this.head);
            } else {
                current.next = new Node<>(e, current.next);
            }
            ++index;
            current = current.next;
        }
        return true;
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
    public void clear() { // O(1)
        this.head = null;
        this.size = 0;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException { // O(n)
        validateIndex(index); // O(1)
        Node<E> current = getNode(index); // O(n)
        return current.data;
    }

    /**
     * Helper method to reduce duplicate code. Finds and returns the
     * node at a given index
     *
     * @param index the index of the node
     * @return the Node at the given index
     */
    private Node<E> getNode(int index) {
        Node<E> current = this.head;
        for (int i = 0; i < index; ++i) {
            current = current.next;
        }
        return current;
    }

    private void validateIndex(int index) throws IndexOutOfBoundsException {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public E set(int index, E element) { // O(n)
        validateIndex(index); // O(1)
        Node<E> current = getNode(index); // O(n)
        E old = current.data;
        current.data = element;
        return old;
    }

    @Override
    public void add(int index, E element) { // O(n)
        if (index != this.size) {
            validateIndex(index); // O(1)
        }
        if (index == 0) {
            this.head = new Node<>(element, this.head);
        }
        Node<E> current = getNode(index - 1); // O(n)
        current.next = new Node<>(element, current.next);
        ++this.size;
    }

    @Override
    public E remove(int index) {
        validateIndex(index); // O(1)
        E result;
        if (index == 0) { // removing head
            result = this.head.data;
            this.head = this.head.next;
            --this.size;
        } else {
            Node<E> previous = getNode(index - 1); // O(n)
            result = previous.next.data; // store the data for the node we are going to remove
            previous.next = previous.next.next; // point the previous node's next to the
        }                                       //removed node's next
        --this.size;
        return result;
    }

    @Override
    public int indexOf(Object o) {
        Node<E> current = this.head;
        for (int i = 0; i < this.size; ++i) { // O(n)
            if (current.data.equals(o)) {
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<E> current = this.head;
        int last = -1;
        for (int i = 0; i < this.size; ++i) { // O(n)
            if (current.data.equals(o)) {
                last = i;
            }
            current = current.next;
        }
        return last;
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
