package ru.job4j.generics;

import java.util.Iterator;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {
    private final T[] array;
    private int size = 0;
    private int marker = 0;

    public SimpleArray(int length) {
        array = (T[]) new Object[length];
    }

    public void add(T model) {
        if (iterator().hasNext()) {
            array[marker] = model;
            size++;
        }
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
            public T next() {  //так как в 75 % случаев в методах класса SimpleArray мы используем index,
                return null;   //т.е. напрямую указываем элемент, то метод next не используется.
            }

            @Override
            public boolean hasNext() {  //метод hasNext используется только в методе add, чтобы найти первый !null
                marker = 0;
                while (array[marker] != null && marker < array.length - 1) {
                    marker++;
                }
                return size < array.length;
            }
        };
    }
}
