package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public abstract class Parking<T extends Car> {
    private List<T> cars = new ArrayList<>();
    private int maxCount;

    public Parking(int maxCount) {
        this.maxCount = maxCount;
    }

    public boolean park(T car) {
        return false;
    }

    public List<T> getCars() {
        return new ArrayList<>(cars);
    }

    private int indexOf(T car) {
        return -1;
    }
}
