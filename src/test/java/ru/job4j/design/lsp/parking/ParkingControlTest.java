package ru.job4j.design.lsp.parking;

import org.junit.Before;
import org.junit.Ignore;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParkingControlTest {
    ParkingControl<Parking<Car>> control;
    PassengerCarParking<Car> passengerParking;
    TruckParking<Car> truckParking;
    PassengerCar passengerCar;

    @Before
    public void itinFields() {
        passengerParking = new PassengerCarParking<>(3);
        truckParking = new TruckParking<>(10);
        control = new ParkingControl<>(List.of(passengerParking, truckParking));
        passengerCar = new PassengerCar("Toyota Corolla");
    }
    @Ignore
    public void whenParkingPassengerCar() {
        control.distribute(passengerCar);
        assertThat(passengerParking.getCars(), is(List.of(passengerCar)));
    }

    @Ignore
    public void whenParkingTruck() {
        Truck truck = new Truck("Actros", 2);
        control.distribute(truck);
        assertThat(truckParking.getCars(), is(List.of(truck)));
    }

    @Ignore
    public void whenNotPlaceForParking() {
        PassengerCar passengerCar = new PassengerCar("Toyota Corolla");
        Truck truck = new Truck("Actros", 6);
        control.distribute(passengerCar);
        control.distribute(passengerCar);
        control.distribute(passengerCar);
        assertThat(control.distribute(passengerCar), is(false));
        assertThat(control.distribute(truck), is(true));
        assertThat(control.distribute(truck), is(false));
    }
}
