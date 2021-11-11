package ru.job4j.design.lsp.parking;

import java.util.ArrayList;
import java.util.List;

public interface Parking<T extends Car> {

    boolean park(T car);
    List<T> getCars();
    int getMaxCount();
}
