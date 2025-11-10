/*
 * Course: CSC-1120
 * Simple Maps and Sets
 * SJMap
 * Name: Sean Jones
 * Last Updated: 11-11-25
 */
package week11;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Simple, incomplete implementation of a Map. Missing several null checks
 * and the add method behavior is not completely correct.
 * @param <K> the element type of the key
 * @param <V> the element type of the value
 */
public class SJMap<K, V> implements Map<K, V> {
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
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public String toString() {
            return "key=" + key + " value=" + value;
        }
    }
    private final List<Entry<K, V>> list;

    /**
     * Simple no-param constructor that initializes list as a new ArrayList
     */
    public SJMap() {
        list = new ArrayList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        for(Entry<K, V> e : list) {
            if(e.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for(Entry<K, V> e : list) {
            if(e.value.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        for(Entry<K, V> e : list) {
            if(e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        if(key == null || containsKey(key)) {
            return null;
        }
        Entry<K, V> entry = new Entry<>(key, value);
        list.add(entry);
        return value;
    }

    @Override
    public V remove(Object key) {
        if(key == null) {
            return null;
        }
        for(Entry<K, V> e : list) {
            if(e.key.equals(key)) {
                list.remove(e);
                return e.value;
            }
        }
        return null;
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
        this.list.clear();
    }

    @Override
    public Set<K> keySet() {
        return list.stream().map(e -> e.key).collect(Collectors.toSet());
    }

    @Override
    public Collection<V> values() {
        return list.stream().map(e -> e.value).toList();
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return new HashSet<>(list);
    }
}