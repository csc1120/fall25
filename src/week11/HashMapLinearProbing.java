/*
 * Course: CSC-1120
 * Hash Maps
 * SJHashMapLinearProbing
 * Name: Sean Jones
 * Last Updated: 11-10-25
 */
package week11;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple HashMap implementation using open addressing and linear probing
 * @param <K> the element type of the ley
 * @param <V> the element type of the value
 */
public class HashMapLinearProbing<K, V> implements Map<K, V> {
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
    private static final double LOAD_FACTOR_THRESHOLD = 75.0;
    private Entry<K, V>[] entries;
    private int numKeys;
    private int numDeleted;
    private final Entry<K, V> deleted;

    /**
     * No-param constructor that sets the starting capacity at 11
     */
    @SuppressWarnings("unchecked")
    public HashMapLinearProbing() {
        final int startingCapacity = 11;
        entries = new Entry[startingCapacity];
        numKeys = 0;
        numDeleted = 0;
        deleted = new Entry<>(null, null);
    }

    @Override
    public int size() {
        return this.numKeys;
    }

    @Override
    public boolean isEmpty() {
        return this.numKeys == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        int index = findIndex(key);
        while(this.entries[index] != null && !this.entries[index].key.equals(key)) {
            index = ++index % this.entries.length;
        }
        return this.entries[index] != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for(Entry<K, V> e : entries) {
            if(e != null && e.value.equals(value)) {
                return true;
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
        while(this.entries[index] != null && !this.entries[index].key.equals(key)) {
            index = ++index % this.entries.length;
        }
        return this.entries[index] == null ? null : this.entries[index].value;
    }

    @Override
    public V put(K key, V value) {
        if(loadFactor() >= LOAD_FACTOR_THRESHOLD) {
            rehash();
        }
        if(key == null) {
            return null;
        }
        int index = findIndex(key);
        while(this.entries[index] != null && !this.entries[index].key.equals(key)) {
            index = ++index % this.entries.length;
        }

        if(this.entries[index] == null) {
            ++numKeys;
            this.entries[index] = new Entry<>(key, value);
            return value;
        } else {
            V old = this.entries[index].value;
            this.entries[index].value = value;
            return old;
        }
    }

    @Override
    public V remove(Object key) {
        if(key == null) {
            return null;
        }
        int index = findIndex(key);
        while(this.entries[index] != null && !this.entries[index].key.equals(key)) {
            index = ++index % this.entries.length;
        }
        if(this.entries[index] == null) {
            return null;
        } else {
            --this.numKeys;
            ++this.numDeleted;
            V result = this.entries[index].value;
            this.entries[index] = deleted;
            return result;
        }
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Set<? extends Map.Entry<? extends K, ? extends V>> set = m.entrySet();
        for(Map.Entry<? extends K, ? extends V> e : set) {
            this.put(e.getKey(), e.getValue());
        }
    }

    @Override
    public void clear() {
        Arrays.fill(entries, null);
        numDeleted = 0;
        numKeys = 0;
    }

    @Override
    public Set<K> keySet() {
        return Arrays.stream(entries).map(e -> e.key).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return Arrays.stream(entries).map(e -> e.value).collect(Collectors.toSet());
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return Arrays.stream(entries).collect(Collectors.toSet());
    }

    private int findIndex(Object key) {
        int index = key.hashCode() % this.entries.length;
        if(index < 0) {
            index += this.entries.length;
        }
        return index;
    }

    private double loadFactor() {
        return (double) (numKeys + numDeleted) / this.entries.length;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        Entry<K, V>[] oldEntries = this.entries;
        this.entries = new Entry[entries.length * 2];
        this.numKeys = 0;
        this.numDeleted = 0;
        for(Entry<K, V> e : oldEntries) {
            if(e != null && e != deleted) {
                put(e.key, e.value);
            }
        }
    }
}
