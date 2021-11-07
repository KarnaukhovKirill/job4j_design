package ru.job4j.design.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Warehouse implements Storage<Food> {
    private List<Food> foods = new ArrayList<>();

    @Override
    public void save(Food food) {
        if (quality(food)) {
            foods.add(food);
        }
    }

    @Override
    public List<Food> getFoods() {
        return foods;
    }

    @Override
    public boolean quality(Food food) {
        return food.percent() < 25;
    }
}
