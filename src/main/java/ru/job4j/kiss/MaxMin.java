package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return find(value, comparator);
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return find(value, comparator.reversed());
    }

    private <T> T find(List<T> value, Comparator<T> comparator) {
        T rsl = value.get(0);
        for (int i = 1; i < value.size(); i++) {
            T obj = value.get(i);
            if (comparator.compare(obj, rsl) > 0) {
                rsl = obj;
            }
        }
        return rsl;
    }
}
