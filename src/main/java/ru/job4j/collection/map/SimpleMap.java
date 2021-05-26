package ru.job4j.collection.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {
    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        int index = (key == null) ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null) {
            return false;
        }
        table[index] = new MapEntry<>(key, value);
        count++;
        modCount++;
        if ((float) count / capacity >= LOAD_FACTOR) {
            grow();
        }
        return true;
    }

    private void grow() { //added Method
        MapEntry<K, V>[] buffer = table;
        table = new MapEntry[table.length * 2];
        capacity = table.length * 2;
        count = 0;
        for (MapEntry<K, V> map : buffer) {
            if (map != null) {
                this.put(map.key, map.value);
            }
        }
    }

    private int hash(int hashCode) {
        return (hashCode()) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (table.length - 1);
    }

    @Override
    public V get(K key) {
        int index = (key == null) ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null && Objects.equals(key, table[index].key)) {
            return table[index].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        int index = (key == null) ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] != null && Objects.equals(key, table[index].key)) {
            table[index] = null;
            count--;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<>() {
            private int index = 0;
            private final int expectedMC = modCount;

            @Override
            public boolean hasNext() {
                if (expectedMC != modCount) {
                    throw new ConcurrentModificationException();
                }
                boolean rsl = false;
                for (int i = index; i < table.length; i++) {
                    if (table[i] != null) {
                        index = i;
                        rsl = true;
                        break;
                    }
                }
                return rsl;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
