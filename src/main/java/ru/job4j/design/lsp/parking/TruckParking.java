package ru.job4j.design.lsp.parking;

public class TruckParking<T extends Car> extends Parking<T> {
    public TruckParking(int maxCount) {
        super(maxCount);
    }
}
