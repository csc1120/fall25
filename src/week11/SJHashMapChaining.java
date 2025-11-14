/*
 * Course: CSC-1120
 * Assignment name
 * File name
 * Name: Sean Jones
 * Last Updated:
 */
package week11;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class SJHashMapChaining<K, V> implements Map<K, V> {
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

    private LinkedList<Entry<K, V>>[] entries;
    private int numKeys;

    public SJHashMapChaining() {
        final int startingCapacity = 11;
        numKeys = 0;
        entries = new LinkedList[startingCapacity];
    }


    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean containsKey(Object key) {
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
        for(LinkedList<Entry<K, V>> list : entries) { // O(m)
            if(list != null) {
                for(Entry<K, V> e : list) { // O(p)
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
        int index = findIndex(key);
        if(entries[index] != null) {
            for (Entry<K, V> e : entries[index]) {
                if (Objects.equals(e.key, key)) {
                    return e.value;
                }
            }
        }
        return null;
    }

    @Override
    public @Nullable V put(K key, V value) {
        if(key != null) {
            int index = findIndex(key);
            if(entries[index] == null) {
                entries[index] = new LinkedList<>();
            }
            for(Entry<K, V> e : entries[index]) {
                if(Objects.equals(e.key, key)) {
                    // duplicate key
                    V old = e.value;
                    e.value = value;
                    return old;
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
                    if(Objects.equals(e.key, key)) {
                        // remove it
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
    public void putAll(@NotNull Map<? extends K, ? extends V> m) {

    }

    @Override
    public void clear() {

    }

    @Override
    public @NotNull Set<K> keySet() {
        return Arrays.stream(entries)
                .flatMap(list -> list.stream())
                .map(e -> e.key)
                .collect(Collectors.toSet());
    }

    @Override
    public @NotNull Collection<V> values() {
        return Arrays.stream(entries)
                .flatMap(list -> list.stream())
                .map(e -> e.value)
                .toList();
    }

    @Override
    public @NotNull Set<Map.Entry<K, V>> entrySet() {
        return Arrays.stream(entries)
                .flatMap(list -> list.stream())
                .collect(Collectors.toSet());
    }

    private int findIndex(Object key) {
        int index = key.hashCode() % entries.length;
        if (index < 0) {
            index += entries.length;
        }
        return index;
    }
}
