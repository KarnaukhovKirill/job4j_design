package ru.job4j.design.lsp.parking;

public class PassengerCarParking<T extends Car> extends Parking<T> {

    public PassengerCarParking(int maxCount) {
        super(maxCount);
    }
}
