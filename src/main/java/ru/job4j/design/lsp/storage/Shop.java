package ru.job4j.design.lsp.storage;

public class Shop<T extends Food> extends Storage<T> {

    @Override
    public boolean canAccept(T food) {
        var quality = this.getPercent(food);
        if (quality > 75 && quality < 100) {
            food.setDiscount(30);
        }
        return quality >= 25 && quality <= 75;
    }
}
