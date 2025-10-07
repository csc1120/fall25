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

    private Node<E> head;
    private int size;

    /**
     * Construtor for the list
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
        return null;
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
    public E get(int index) throws IndexOutOfBoundsException {
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
