/*
 * Course: CSC-1120
 * ArrayList
 * SJArrayList
 * Name: Sean Jones
 * Last Updated: 09-29-25
 */
package week5;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.RandomAccess;
import java.util.SequencedCollection;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * A simple implementation of an ArrayList
 *
 * @param <E> the element stored in the ArrayList
 */
@SuppressWarnings("unused")
public class SJArrayList<E> implements Serializable, Cloneable, Iterable<E>, List<E>,
        RandomAccess, SequencedCollection<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private E[] data;
    private int size;

    /**
     * No param-constructor that defaults the starting capacity of the
     * backing array to 10
     */
    public SJArrayList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructor with one parameter that will set the starting
     * capacity of the list
     *
     * @param startingCapacity the starting capacity of the list
     */
    @SuppressWarnings("unchecked")
    public SJArrayList(int startingCapacity) {
        this.size = 0;
        this.data = (E[]) new Object[startingCapacity];
    }

    /**
     * Constructor with one parameter that will create a new List containing
     * all the elements contained in the Collection parameter
     *
     * @param c a Collection of elements to add
     */
    @SuppressWarnings("unchecked")
    public SJArrayList(Collection<? extends E> c) {
        Object[] a = c.toArray();
        if ((size = a.length) != 0) {
            if (c.getClass() == SJArrayList.class) {
                this.data = (E[]) a;
            } else {
                this.data = (E[]) Arrays.copyOf(a, size, Object[].class);
            }
        } else {
            // replace with empty array.
            this.data = (E[]) new Object[0];
        }
    }

    @Override
    public int size() { // O(1)
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0; // O(1)
    }

    @Override
    public boolean contains(Object o) { // O(n)
        for (int i = 0; i < this.size; ++i) {
            if (this.data[i].equals(o)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return new SJIterator();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[this.size];
        System.arraycopy(this.data, 0, result, 0, this.size);
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if(a.length < this.size) {
            return (T[]) Arrays.copyOf(this.data, size, a.getClass());
        }
        T[] array = (T[]) this.data;
        System.arraycopy(array, 0, a, 0, this.size);
        if (a.length > this.size) {
            a[this.size] = null;
        }
        return a;
    }

    @Override
    public boolean add(E e) { // O(1)
        if (this.size == this.data.length) {
            reallocate(); // make the backing array bigger
        }
        this.data[size++] = e; // increment size after adding
        return true;
    }

    @SuppressWarnings("unchecked")
    private void reallocate() { // O(n)
        E[] temp = (E[]) new Object[this.data.length * 2 + 1];
        System.arraycopy(this.data, 0, temp, 0, this.data.length);
        this.data = temp;
    }

    @Override
    public boolean remove(Object o) { // O(n)
        for (int i = 0; i < this.size; ++i) {
            if (this.data[i].equals(o)) {
                // remove it
                for (int j = i; j < this.size - 1; ++j) {
                    this.data[j] = this.data[j + 1]; // shift all later indexes left
                }
                --this.size; // decrement size
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c) {
            if (!this.contains(o)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (E e : c) {
            this.add(e);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        for (E e : c) {
            this.add(index++, e);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean changed = false;
        for (Object o : c) {
            if (this.remove(o)) {
                changed = true;
            }
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Iterator<E> it = this.iterator();
        boolean changed = false;
        while (it.hasNext()) {
            E e = it.next();
            if (!c.contains(e)) {
                it.remove();
                changed = true;
            }
        }
        return changed;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() { // O(1)
        this.data = (E[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public E get(int index) throws IndexOutOfBoundsException { // O(1)
        validateIndex(index); // validate the index
        return this.data[index]; // return the element at the index
    }

    @Override
    public E set(int index, E element) throws IndexOutOfBoundsException { // O(1)
        validateIndex(index); // validate the index
        E result = this.data[index]; // store the old element
        this.data[index] = element; // set the new element
        return result; // return the old element
    }

    @Override
    public void add(int index, E element) throws IndexOutOfBoundsException {
        // what is the Big-O?
        validateIndex(index); // validate the index
        if (this.size == this.data.length) { // make sure we have room
            reallocate();
        }
        // shift all later elements to the right to make room for the new element
        for (int i = this.size - 1; i >= index; --i) {
            this.data[i + 1] = this.data[i];
        }
        this.data[index] = element; // add the element at the index
        ++this.size; // increment size
    }

    @Override
    public E remove(int index) throws IndexOutOfBoundsException {
        // what is the Big-O?
        validateIndex(index); // validate the index
        E result = this.data[index];
        // shift all the later elements left to close the gap
        for (int i = index; i < this.size - 1; ++i) {
            this.data[i] = this.data[i + 1];
        }
        --this.size; // decrement size
        return result; // return the removed element
    }

    @Override
    public int indexOf(Object o) {
        for(int i = 0; i < this.size; ++i) {
            if(this.data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for(int i = this.size - 1; i >= 0; --i) {
            if(this.data[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return new SJListIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) throws IndexOutOfBoundsException {
        return new SJListIterator(index);
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    /*
     * Default Methods - These methods are already implemented in the interface
     * is default methods. If a specific implementation is needed, the default methods
     * may be overridden here.
     *
     * Default methods not overridden here:
     *     replaceAll()
     *     sort()
     *     spliterator()
     */


    @Override
    public void addFirst(E e) {
        this.add(0, e);
    }

    @Override
    public void addLast(E e) {
        this.add(e);
    }

    @Override
    public E getFirst() {
        return this.get(0);
    }

    @Override
    public E getLast() {
        return this.get(this.size - 1);
    }

    @Override
    public E removeFirst() {
        return this.remove(0);
    }

    @Override
    public E removeLast() {
        return this.remove(this.size - 1);
    }

    @Override
    public List<E> reversed() {
        SJArrayList<E> reversed = new SJArrayList<>();
        for (int i = this.size - 1; i >= 0; --i) {
            reversed.add(this.get(i));
        }
        return reversed;
    }

    /*
     * Additional methods added to the java.util.ArrayList class
     */

    /**
     * Trims the capacity of this ArrayList instance to be the list's current size.
     * An application can use this operation to minimize the storage of an ArrayList instance.
     */
    @SuppressWarnings("unchecked")
    public void trimToSize() {
        E[] trimmed = (E[]) new Object[this.size];
        System.arraycopy(this.data, 0, trimmed, 0, this.size);
        this.data = trimmed;
    }

    /**
     * Increases the capacity of this ArrayList instance, if necessary, to ensure that it can
     * hold at least the number of elements specified by the minimum capacity argument.
     *
     * @param minCapacity - the desired minimum capacity
     */
    @SuppressWarnings("unchecked")
    public void ensureCapacity(int minCapacity) {
        if (this.data.length < minCapacity) {
            E[] newData = (E[]) new Object[minCapacity];
            System.arraycopy(this.data, 0, newData, 0, this.size);
            this.data = newData;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object clone() throws InternalError {
        try {
            SJArrayList<E> v = (SJArrayList<E>) super.clone();
            v.data = Arrays.copyOf(this.data, this.size);
            return v;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof List)) {
            return false;
        }
        Iterator<?> it = ((List<?>) o).iterator();
        for (int i = 0; i < this.size; ++i) {
            if (!it.hasNext() || !Objects.equals(this.data[i], it.next())) {
                return false;
            }
        }
        return !it.hasNext();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int hashCode = 1;
        for (int i = 0; i < this.size; ++i) {
            Object e = this.data[i];
            hashCode = prime * hashCode + (e == null ? 0 : e.hashCode());
        }
        return hashCode;
    }

    /**
     * Removes from this list all the elements whose index is between fromIndex, inclusive,
     * and toIndex, exclusive. Shifts any succeeding elements to the left (reduces their index).
     * This call shortens the list by (toIndex - fromIndex) elements. (If toIndex==fromIndex,
     * this operation has no effect.)
     *
     * @param fromIndex - index of first element to be removed
     * @param toIndex   - index after last element to be removed
     * @throws IndexOutOfBoundsException - if fromIndex or toIndex is out of range
     *                                   (fromIndex < 0 || toIndex > size() || toIndex < fromIndex)
     */
    public void removeRange(int fromIndex, int toIndex) throws IndexOutOfBoundsException {
        validateIndex(fromIndex);
        validateIndex(toIndex);
        if (toIndex < fromIndex) {
            throw new IndexOutOfBoundsException();
        }
        Iterator<E> it = this.iterator();
        for (int i = 0; i < fromIndex; ++i) {
            it.next();
        }
        while (fromIndex < toIndex) {
            it.remove();
            ++fromIndex;
        }
    }

    @Override
    public void forEach(Consumer<? super E> action) {
        for (E e : this) {
            action.accept(e);
        }
    }

    @Override
    public boolean removeIf(Predicate<? super E> filter) {
        Iterator<E> it = this.iterator();
        boolean changed = false;
        while(it.hasNext()) {
            if(filter.test(it.next())) {
                it.remove();
                changed = true;
            }
        }
        return changed;
    }

    private void validateIndex(int index) throws IndexOutOfBoundsException {
        if (index >= this.size || index < 0) { // O(1)
            throw new IndexOutOfBoundsException("Index " + index + " is invalid for" +
                    " a list of size " + this.size);
        }
    }

    private class SJIterator implements Iterator<E> {
        private int index;
        private int lastReturned;

        private SJIterator() {
            this.index = 0;
            this.lastReturned = -1;
        }

        @Override
        public boolean hasNext() {
            return this.index < SJArrayList.this.size;
        }

        @Override
        public E next() {
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            }
            ++this.lastReturned;
            return SJArrayList.this.data[index++];
        }

        @Override
        public void remove() {
            if (this.lastReturned < 0) {
                throw new IllegalStateException();
            }
            SJArrayList.this.remove(this.index);
            this.lastReturned = -1;
        }

        @Override
        public void forEachRemaining(Consumer<? super E> action) {
            while (this.hasNext()) {
                action.accept(this.next());
            }
        }
    }

    private class SJListIterator extends SJIterator implements ListIterator<E> {
        private SJListIterator(int index) {
            super();
            super.index = index;
        }

        @Override
        public boolean hasPrevious() {
            return super.index != 0;
        }

        @Override
        public E previous() {
            int i = super.index - 1;
            if (i < 0) {
                throw new NoSuchElementException();
            }
            super.index = i;
            return SJArrayList.this.data[super.lastReturned = i];
        }

        @Override
        public int nextIndex() {
            return super.index;
        }

        public int previousIndex() {
            return super.index - 1;
        }

        @Override
        public void set(E e) {
            if (super.lastReturned < 0) {
                throw new IllegalStateException();
            }
            SJArrayList.this.set(super.lastReturned, e);
        }

        @Override
        public void add(E e) {
            int i = super.index;
            SJArrayList.this.add(i, e);
            super.index = i + 1;
            super.lastReturned = -1;
        }
    }
}
