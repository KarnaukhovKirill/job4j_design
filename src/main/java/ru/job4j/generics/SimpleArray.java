package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] array;
    private int size = 0;
    private int marker = 0;

    public SimpleArray(int length) {
        array = (T[]) new Object[length];
    }

    public void add(T model) {
        marker = 0;
        while (iterator().hasNext()) {
            marker++;
        }
        array[marker] = model;
        size++;
        marker = 0;
    }

    public T get(int index) {
        int i = Objects.checkIndex(index, size);
        return array[i];
    }

    public void remove(int index) {
        int i = Objects.checkIndex(index, size);
        System.arraycopy(array, i + 1, array, index, array.length - index - 1);
        array[size - 1] = null;
        size--;
    }

    public void set(int index, T model) {
        int i = Objects.checkIndex(index, size);
        array[i] = model;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[marker++];
            }

            @Override
            public boolean hasNext() {
                return marker < size;
            }
        };
    }
}
