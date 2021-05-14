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
        int index = findIndexById(id);
        if (index < 0) {
            return false;
        } else {
            mem.set(index, model);
        }
        return true;
    }

    @Override
    public boolean delete(String id) {
        int index = findIndexById(id);
        if (index < 0) {
            return false;
        } else {
            mem.remove(index);
        }
        return true;
    }

    @Override
    public T findById(String id) {
        int index = findIndexById(id);
        if (index < 0) {
            return null;
        }
        return mem.get(index);
    }

    public int findIndexById(String id) {
        int rsl = -1;
            for (int index = 0; index < mem.size(); index++) {
                if (mem.get(index).getId().equals(id)) {
                    rsl = index;
                    break;
                }
            }
        return rsl;
    }
}
