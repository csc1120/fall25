/*
 * Course: CSC-1120
 * Hash Maps
 * SJHashMapLinearProbing
 * Name: Sean Jones
 * Last Updated:
 */
package week11;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * A simple HashMap implementation using linear probing
 * @param <K> the element type for the key
 * @param <V> the element type for the value
 */
public class SJHashMapLinearProbing<K, V> implements Map<K, V> {
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

    private Entry<K, V>[] entries;
    private int numKeys;

    /**
     * No-param constructor that sets the starting capacity at 11
     */
    @SuppressWarnings("unchecked")
    public SJHashMapLinearProbing() {
        final int startingCapacity = 11;
        entries = new Entry[startingCapacity];
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
        while(entries[index] != null && !entries[index].key.equals(key)) {
            index = ++index % entries.length;
        }
        return entries[index] != null;
    }

    @Override
    public boolean containsValue(Object value) {
        // linear search
        return false;
    }

    @Override
    public V get(Object key) {
        return null;
    }

    @Override
    public V put(K key, V value) {
        return null;
    }

    @Override
    public V remove(Object key) {
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public Collection<V> values() {
        return List.of();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return Set.of();
    }

    private int findIndex(Object key) {
        int index = key.hashCode() % entries.length;
        if(index < 0) {
            index += entries.length;
        }
        return index;
    }
}
