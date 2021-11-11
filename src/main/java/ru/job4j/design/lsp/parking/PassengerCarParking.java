package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public class PassengerCarParking<T extends Car> implements Parking<T> {
    private List<T> cars = new ArrayList<>();
    private int maxCount;

    public PassengerCarParking(int maxCount) {
        this.maxCount = maxCount;
    }

    @Override
    public boolean park(T car) {
        cars.add(car);
        maxCount -= car.getSize();
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
