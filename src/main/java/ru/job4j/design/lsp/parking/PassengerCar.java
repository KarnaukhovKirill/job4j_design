package ru.job4j.design.lsp.parking;

public class PassengerCar extends Car {
    public static final int DEFAULT_SIZE = 1;

    public PassengerCar(String name) {
        super(name, DEFAULT_SIZE);
    }
}
