package ru.job4j.design.lsp.storage;

import java.util.Calendar;
import java.util.TimeZone;

public class Trash<T extends Food> extends Storage<T> {

    @Override
    public boolean canAccept(T food) {
        Calendar now = Calendar.getInstance();
        now.setTimeZone(TimeZone.getDefault());
        return now.after(food.getExpiryDate());
    }
}
