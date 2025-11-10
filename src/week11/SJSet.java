/*
 * Course: CSC-1120
 * ASSIGNMENT
 * CLASS
 * Name: Sean Jones
 * Last Updated:
 */
package week11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * A simple Set implementation using a backing ArrayList
 * @param <E> the element type stored in the set
 */
public class SJSet<E> implements Set<E> {
    private final List<E> data = new ArrayList<>();

    @Override
    public int size() {
        return this.data.size();
    }

    @Override
    public boolean isEmpty() {
        return this.data.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.data.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return this.data.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.data.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return this.data.toArray(a);
    }

    @Override
    public boolean add(E e) {
        if(!this.contains(e)) {
            return data.add(e);
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        return this.data.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return new HashSet<>(this.data).containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        // Union operation
        boolean changed = false;
        for(E e : c) {
            changed = changed || this.add(e);
        }
        return changed;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // Intersection operation
        boolean changed = false;
        for(E e : this.data) {
            if(!c.contains(e)) {
                changed = changed || this.data.remove(e);
            }
        }
        return changed;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        // Difference operation
        boolean changed = false;
        for(E e : this.data) {
            if(c.contains(e)) {
                changed = changed || this.data.remove(e);
            }
        }
        return changed;
    }

    @Override
    public void clear() {
        this.data.clear();
    }
}
