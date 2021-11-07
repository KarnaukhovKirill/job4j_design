package ru.job4j.design.lsp.storage;

import java.util.List;

public class ControlQuality<T extends Storage<Food>> {
    private List<T> storages;

    public ControlQuality(List<T> storages) {
        this.storages = storages;
    }

    public void distribute(Food food) {
        storages.forEach(s -> s.save(food));
    }
}
