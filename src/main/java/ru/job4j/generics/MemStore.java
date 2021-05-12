package ru.job4j.generics;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        T obj = findById(id);
        if (obj == null) {
            return false;
        } else {
            mem.set(mem.indexOf(obj), model);
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        T obj = findById(id);
        if (obj == null) {
            return false;
        } else {
            mem.remove(obj);
        }
        return false;
    }

    @Override
    public T findById(String id) {
        int index = 0;
        for (T m : mem) {
            if (id.equals(m.getId())) {
                return mem.get(index);
            } else {
                index++;
            }
        }
        return null;
    }
}
