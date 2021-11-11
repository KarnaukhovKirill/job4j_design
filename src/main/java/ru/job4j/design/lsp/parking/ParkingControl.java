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
        if (car.getSize() == PassengerCar.DEFAULT_SIZE && passengerParking.getMaxCount() >= 1) {
            return passengerParking.park(car);
        }
        if (car.getSize() > PassengerCar.DEFAULT_SIZE && truckParking.getMaxCount() >= 1) {
            return truckParking.park(car);
        } else if (car.getSize() <= passengerParking.getMaxCount()) {
            return passengerParking.park(car);
        }
        return false;
    }

    public List<T> getPassengerCars() {
        return passengerParking.getCars();
    }

    public List<T> getTrucks() {
        return truckParking.getCars();
    }
}
