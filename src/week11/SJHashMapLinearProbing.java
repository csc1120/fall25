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
 *
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

    private static final double LOAD_FACTOR_THRESHOLD = 0.75;
    private Entry<K, V>[] entries;
    private final Entry<K, V> deleted = new Entry<>(null, null);
    private int numKeys;
    private int numDeletes;

    /**
     * No-param constructor that sets the starting capacity at 11
     */
    @SuppressWarnings("unchecked")
    public SJHashMapLinearProbing() {
        final int startingCapacity = 11;
        entries = new Entry[startingCapacity];
        numKeys = 0;
        numDeletes = 0;
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
        if (key == null) {
            return false;
        }
        int index = findIndex(key);
        while (entries[index] != null && !entries[index].key.equals(key)) {
            index = ++index % entries.length;
        }
        return entries[index] != null;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Entry<K, V> e : entries) {
            if (e != null && e.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int index = findIndex(key);
        while (entries[index] != null && !entries[index].key.equals(key)) {
            index = ++index % entries.length;
        }
        return entries[index] == null ? null : entries[index].value;
    }

    @Override
    public V put(K key, V value) {
        // check if enough room
        if ((double) (numKeys + numDeletes) / entries.length >= LOAD_FACTOR_THRESHOLD) {
            rehash();
        }
        int index = findIndex(key);
        // find null
        while (entries[index] != null && entries[index].key != null &&
                !entries[index].key.equals(key)) {
            index = ++index % entries.length;
        }
        V result = value;
        // check if exists
        if(entries[index].key.equals(key)) {
            // exists
            // replace the value
            result = entries[index].value;
            entries[index].value = value;
        } else {
            // add it
            entries[index] = new Entry<>(key, value);
        }
        ++numKeys;
        return result;
    }

    @Override
    public V remove(Object key) {
        if(key == null) {
            return null;
        }
        int index = findIndex(key);
        while (entries[index] != null && entries[index].key != null
                && !entries[index].key.equals(key)) {
            index = ++index % entries.length;
        }
        if(entries[index] == null) {
            return null;
        }
        // remove it
        V result = entries[index].value;
        entries[index] = deleted;
        --numKeys;
        ++numDeletes;
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
        if (index < 0) {
            index += entries.length;
        }
        return index;
    }

    private void rehash() {
        Entry<K, V>[] old = entries;
        entries = new Entry[entries.length * 2];
        numKeys = 0;
        for(Entry<K, V> e : old) {
            if(e != null && e.key != null) {
                this.put(e.key, e.value);
            }
        }
    }
}
