package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] container;
    private int size = 0;
    private int modCount = 0;

    public SimpleArray(int length) {
        container = new Object[length];
    }

    public SimpleArray() {
        container = new Object[10];
    }

    public T get(int index) {
        int i = Objects.checkIndex(index, size);
        return (T) container[i];
    }

    public void add(T model) {
        modCount++;
        if (size == container.length) {
            container = grow();
        }
        container[size] = model;
        size++;
    }

    private Object[] grow() {
        return Arrays.copyOf(container, container.length + container.length / 2);
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
                return (T) container[index++];
            }
        };
    }
}
