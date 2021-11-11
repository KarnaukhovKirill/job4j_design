package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class TruckParking<T extends Car> implements Parking<T> {
    private List<T> cars = new ArrayList<>();
    private int maxCount;

    public TruckParking(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public boolean park(T car) {
        cars.add(car);
        maxCount--;
        return true;
    }

    @Override
    public List<T> getCars() {
        return new ArrayList<>(cars);
    }

    @Override
    public int getMaxCount() {
        return maxCount;
    }
}
