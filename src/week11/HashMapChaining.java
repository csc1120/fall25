/*
 * Course: CSC-1120
 * ASSIGNMENT
 * CLASS
 * Name: Sean Jones
 * Last Updated:
 */
package week11;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A simple implementation of a HashMap using Chained Addressing
 * @param <K> the element type of the key
 * @param <V> the element type of the value
 */
public class HashMapChaining<K, V> implements Map<K, V> {
    private static class Entry<K, V> implements Map.Entry<K, V> {
        private final K key;
        private V value;

        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V old = this.value;
            this.value = value;
            return old;
        }

        @Override
        public String toString() {
            return "key=" + key + " value=" + value;
        }
    }

    private static final double LOAD_FACTOR_THRESHOLD = 2.5;
    private LinkedList<Entry<K, V>>[] entries;
    private int numKeys;

    /**
     * No-param constructor that sets the starting backing array length at 11
     */
    @SuppressWarnings("unchecked")
    public HashMapChaining() {
        final int startingCapacity = 11;
        entries = new LinkedList[startingCapacity];
        numKeys = 0;
    }

    @Override
    public int size() {
        return numKeys;
    }

    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        if(key == null) {
            return false;
        }
        int index = findIndex(key);
        if(entries[index] != null) {
            for (Entry<K, V> e : entries[index]) {
                if (Objects.equals(e.key, key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(LinkedList<Entry<K, V>> list : entries) {
            if(list != null) {
                for(Entry<K, V> e : list) {
                    if(Objects.equals(e.value, value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        if(key == null) {
            return null;
        }
        int index = findIndex(key);
        if(entries[index] != null) {
            for(Entry<K, V> e : entries[index]) {
                if(Objects.equals(e.key, key)) {
                    return e.value;
                }
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if(key != null) {
            if ((double) numKeys / entries.length >= LOAD_FACTOR_THRESHOLD) {
                rehash();
            }
            int index = findIndex(key);
            if (entries[index] == null) {
                entries[index] = new LinkedList<>();
            }
            for (Entry<K, V> e : entries[index]) {
                if (e.key.equals(key)) {
                    V result = e.value;
                    e.value = value;
                    return result;
                }
            }
            entries[index].add(new Entry<>(key, value));
            ++numKeys;
        }
        return null;
    }

    @Override
    public V remove(Object key) {
        if(key != null) {
            int index = findIndex(key);
            if(entries[index] != null) {
                for(Entry<K, V> e : entries[index]) {
                    if(e.key.equals(key)) {
                        entries[index].remove(e);
                        --numKeys;
                        return e.value;
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if(m != null) {
            Set<? extends Map.Entry<? extends K, ? extends V>> set = m.entrySet();
            for (Map.Entry<? extends K, ? extends V> e : set) {
                this.put(e.getKey(), e.getValue());
            }
        }
    }

    @Override
    public void clear() {
        Arrays.fill(entries, null);
        numKeys = 0;
    }

    @Override
    public Set<K> keySet() {
        return Arrays.stream(entries)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(e -> e.key)
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return Arrays.stream(entries)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .map(e -> e.value)
                .toList();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return Arrays.stream(entries)
                .filter(Objects::nonNull)
                .flatMap(List::stream)
                .collect(Collectors.toSet());
    }

    private int findIndex(Object key) {
        int index = key.hashCode() % this.entries.length;
        if(index < 0) {
            index += this.entries.length;
        }
        return index;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        LinkedList<Entry<K, V>>[] old = entries;
        entries = new LinkedList[old.length * 2];
        numKeys = 0;
        for(LinkedList<Entry<K, V>> list : old) {
            if(list != null) {
                for(Entry<K, V> e : list) {
                    this.put(e.key, e.value);
                }
            }
        }
    }
}
