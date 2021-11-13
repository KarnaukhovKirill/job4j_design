package ru.job4j.design.lsp.parking;

import java.util.List;

public class ParkingControl<T extends Car> {
    PassengerCarParking<T> passengerParking;
    TruckParking<T> truckParking;

    public ParkingControl(int passengerMaxCount, int truckMaxCount) {
        passengerParking = new PassengerCarParking<>(passengerMaxCount);
        truckParking = new TruckParking<>(truckMaxCount);
    }

    public boolean distribute(T car) {
        return truckParking.park(car) || passengerParking.park(car);
    }

    public List<T> getPassengerCars() {
        return passengerParking.getCars();
    }

    public List<T> getTrucks() {
        return truckParking.getCars();
    }
}
