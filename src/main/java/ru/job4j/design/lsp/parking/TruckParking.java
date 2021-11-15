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
        boolean result = false;
        if (car.getSize() > PassengerCar.DEFAULT_SIZE && maxCount >= 1) {
            cars.add(car);
            maxCount--;
            result = true;
        }
        return result;
    }

    @Override
    public List<T> getCars() {
        return new ArrayList<>(cars);
    }
}
