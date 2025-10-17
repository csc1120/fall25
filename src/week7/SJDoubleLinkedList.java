/*
 * Course: CSC-1120
 * Double Linked List
 * SJLinkedList
 * Name: Sean Jones
 * Last Updated: 10-09-25
 */
package week7;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * Double Linked List implementation
 *
 * @param <E> the element stored in the list
 */
@SuppressWarnings("unused")
public class SJDoubleLinkedList<E> implements List<E> {
    private static class Node<E> {
        private E data;
        private Node<E> next;
        private Node<E> prev;

        private Node(E data, Node<E> prev, Node<E> next) {
            this.data = data;
            this.next = next;
            this.prev = prev;
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
            this.next = SJDoubleLinkedList.this.head;
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
                SJDoubleLinkedList.this.head = this.next;
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
            --SJDoubleLinkedList.this.size;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            while (this.hasNext()) {
                action.accept(this.next());
            }
        }
    }

    private class ListIter implements ListIterator<E> {
        private Node<E> next;
        private Node<E> lastReturned;
        private int nextIndex;

        private ListIter() {
            this(0);
        }

        private ListIter(int index) {
            this.next = (index == size) ? null : getNode(index);
            this.nextIndex = index;
            this.lastReturned = null;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public E next() {
            if (next == null) {
                throw new NoSuchElementException();
            }
            lastReturned = next;
            next = next.next;
            ++nextIndex;
            return lastReturned.data;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            // If next is null, step back from tail; otherwise step to next.prev
            next = (next == null) ? tail : next.prev;
            lastReturned = next;
            --nextIndex;
            return lastReturned.data;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            Node<E> lrPrev = lastReturned.prev;
            Node<E> lrNext = lastReturned.next;

            if (lrPrev != null) {
                lrPrev.next = lrNext;
            } else {
                head = lrNext;
            }

            if (lrNext != null) {
                lrNext.prev = lrPrev;
            } else {
                tail = lrPrev;
            }

            if (next == lastReturned) {
                next = lrNext;
            } else {
                nextIndex--;
            }
            lastReturned = null;
            --size;
        }

        @Override
        public void set(E e) {
            if (lastReturned == null) {
                throw new IllegalStateException();
            }
            lastReturned.data = e;
        }

        @Override
        public void add(E e) {
            // Insert before 'next' (i.e., at the cursor)
            Node<E> prev = (next == null) ? tail : next.prev;
            Node<E> newNode = new Node<>(e, prev, next);

            if (prev == null) {
                head = newNode;
            } else {
                prev.next = newNode;
            }

            if (next == null) {
                tail = newNode;
            } else {
                next.prev = newNode;
            }

            ++nextIndex;
            ++size;
            lastReturned = null; // per ListIterator contract
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    /**
     * Constructor for the list
     */
    public SJDoubleLinkedList() {
        this.head = null;
        this.tail = null;
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
        // have pointers at each end, work towards the middle
        Node<E> current = head;
        for (int i = 0; i < this.size; ++i) {
            if (current != null) {
                if (current.data.equals(o)) {
                    return true;
                }
                current = current.next;
            }
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
    public boolean add(E e) { // O(1)
        Node<E> temp = new Node<>(e, null, null);
        if (head == null) { // empty list
            this.head = temp;
            this.tail = temp;
        } else { // add to the end of the list
            this.tail.next = new Node<>(e, this.tail, null);
            this.tail = this.tail.next;
        }
        ++this.size;
        return true;
    }

    @Override
    public boolean remove(Object o) { // O(n)
        if (this.head != null) {
            Node<E> current = this.head;
            while (current != null && !current.data.equals(o)) {
                current = current.next;
            }
            if (current != null) {
                // remove it
                // prev
                if(current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    this.head = current.next;
                }
                if(current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    this.tail = current.prev;
                }
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
            tail.next = new Node<>(e, tail, null);
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
                this.head = new Node<>(e, null, this.head);
            } else {
                current.next = new Node<>(e, current, current.next);
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
        this.tail = null;
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
            this.head = new Node<>(element, null, this.head);
        } else if (index == this.size) {
            this.tail.next = new Node<>(element, this.tail, null);
            this.tail = this.tail.next;
        } else if (index == this.size - 1) {
            this.tail.prev.next = new Node<>(element, this.tail.prev, this.tail);
            this.tail.prev = this.tail.prev.next;
        } else {
            Node<E> current = getNode(index - 1); // O(n)
            current.next = new Node<>(element, current, current.next);
            current.next.next.prev = current.next.next;
        }
        ++this.size;
    }

    @Override
    public E remove(int index) {
        validateIndex(index); // O(1)
        E result;
        if (index == 0) { // removing head
            result = this.head.data;
            this.head = this.head.next;
            this.head.prev = null;
        } else {
            Node<E> current = getNode(index); // O(n)
            result = current.data; // store the data for the node we are going to remove
            if(current.prev != null) {
                current.prev.next = current.next;
            } else {
                this.head = current.next;
            }
            if(current.next != null) {
                current.next.prev = current.prev;
            } else {
                this.tail = current.prev;
            }
        }
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
        Node<E> current = this.tail;
        for (int i = this.size - 1; i >= 0; --i) { // O(n)
            if (current.data.equals(o)) {
                return i;
            }
            current = current.prev;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new ListIter();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new ListIter(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }
}
