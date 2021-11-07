package ru.job4j.design.lsp.storage;

import java.util.List;

public interface Storage<T extends Food> {
    void save(T food);
    List<T> getFoods();
    boolean quality(T food);
}
