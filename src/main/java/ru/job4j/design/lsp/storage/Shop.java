package ru.job4j.design.lsp.storage;

import java.util.ArrayList;
import java.util.List;

public class Shop implements Storage<Food> {
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
        var quality = food.percent();
        if (quality > 75 && quality < 100) {
            food.setDiscount(30);
        }
        return quality >= 25 && quality <= 75;
    }
}
