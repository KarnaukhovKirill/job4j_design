package ru.job4j.design.lsp.storage;

import java.util.List;
import java.util.stream.Collectors;

public class ControlQuality<T extends Storage<Food>> {
    private List<T> storages;

    public ControlQuality(List<T> storages) {
        this.storages = storages;
    }

    public void distribute(Food food) {
        storages.forEach(s -> s.save(food));
    }

    public void resort() {
        var temp = storages.stream()
                .flatMap(s -> s.getFoods().stream())
                        .collect(Collectors.toList());
        storages.forEach(Storage::clear);
        temp.forEach(this::distribute);
    }
}
