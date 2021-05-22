package ru.job4j.collection.set;

import ru.job4j.collection.SimpleArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleSet<T> implements Set<T> {
    private SimpleArray<T> set = new SimpleArray<>();
    private int size = 0;
    private int modCount = 0;

    @Override
    public boolean add(T value) {
        if (!contains(value)) {
            set.add(value);
            size++;
            modCount++;
            return true;
        }
        return false;
    }

    @Override
    public boolean contains(T value) {
        for (T t : set) {
            if (t != null) {
                if (t.equals(value)) {
                    return true;
                }
            } else {
                return value == null;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int index = 0;
            private final int expectedMC = modCount;

            @Override
            public boolean hasNext() {
            if (expectedMC != modCount) {
                throw new ConcurrentModificationException();
            }
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return set.get(index++);
            }
        };
    }
}
