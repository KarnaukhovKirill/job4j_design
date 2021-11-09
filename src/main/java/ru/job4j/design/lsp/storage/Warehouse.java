package ru.job4j.design.lsp.storage;

public class Warehouse<T extends Food> extends Storage<T> {

    @Override
    public boolean canAccept(T food) {
        return this.getPercent(food) < 25;
    }
}
