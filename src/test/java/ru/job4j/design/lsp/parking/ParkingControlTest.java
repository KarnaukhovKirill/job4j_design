package ru.job4j.design.lsp.parking;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class ParkingControlTest {
    ParkingControl<Car> control;
    PassengerCar passengerCar;

    @Before
    public void initFields() {
        control = new ParkingControl<>(3, 1);
        passengerCar = new PassengerCar("Toyota Corolla");
    }
    @Test
    public void whenParkingPassengerCar() {
        control.distribute(passengerCar);
        assertThat(control.getPassengerCars(), is(List.of(passengerCar)));
    }

    @Test
    public void whenParkingTruck() {
        Truck truck = new Truck("Actros", 2);
        control.distribute(truck);
        assertThat(control.getTrucks(), is(List.of(truck)));
    }

    @Test
    public void whenNotPlaceForParking() {
        Truck truck = new Truck("Actros", 6);
        assertThat(control.distribute(passengerCar), is(true));
        assertThat(control.distribute(passengerCar), is(true));
        assertThat(control.distribute(passengerCar), is(true));
        assertThat(control.distribute(passengerCar), is(false));
        assertThat(control.getPassengerCars(), is(List.of(passengerCar, passengerCar, passengerCar)));
        assertThat(control.distribute(truck), is(true));
        assertThat(control.distribute(truck), is(false));
    }

    @Test
    public void whenTruckParkOnPassengerParking() {
        Truck truck = new Truck("Actros", 3);
        assertThat(control.distribute(truck), is(true));
        assertThat(control.getTrucks(), is(List.of(truck)));
        assertThat(control.distribute(truck), is(true));
        assertThat(control.getPassengerCars(), is(List.of(truck)));
        assertThat(control.distribute(truck), is(false));
    }
}
