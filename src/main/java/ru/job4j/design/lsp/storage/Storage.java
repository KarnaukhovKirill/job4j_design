package ru.job4j.design.lsp.storage;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public abstract class Storage<T extends Food> {
    private List<T> foods = new ArrayList<>();

    public boolean save(T food) {
        boolean result = false;
        if (canAccept(food)) {
            foods.add(food);
            result = true;
        }
        return result;
    }

    public List<T> getFoods() {
        return new ArrayList<>(foods);
    }

    public abstract boolean canAccept(T food);

    /**
     * Метод getPercent высчитывает процент срока годности. Чем выше процент, тем ближе дата истечения срока годности
     * продукта
     * @param food продукт, у которого мы хотим высчитать процент срока годности
     * @return возвращает процент срока годности.
     */
    public float getPercent(T food) {
        Calendar now = Calendar.getInstance();
        now.setTimeZone(TimeZone.getDefault());
        Duration createToExpire = Duration.between(food.getCreateDate().getTime().toInstant(),
                food.getExpiryDate().getTime().toInstant());
        Duration nowToExpire = Duration.between(now.getTime().toInstant(),
                food.getExpiryDate().getTime().toInstant());
        return 100 - (float) nowToExpire.toDaysPart() / createToExpire.toDaysPart() * 100;
    }

    public void clear() {
        foods.clear();
    }
}
