package ru.job4j.design.isp.menu;

import java.util.List;

public interface Generatable<T> {
    void generate(List<T> list);
}
