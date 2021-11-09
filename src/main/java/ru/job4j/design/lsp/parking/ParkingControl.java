package ru.job4j.design.lsp.parking;

import java.util.List;

public class ParkingControl<T extends Parking<Car>> {
    private List<T> parkingList;

    public ParkingControl(List<T> parkingList) {
        this.parkingList = parkingList;
    }

    public boolean distribute(Car car) {
        return false;
    }
}
