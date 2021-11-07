package ru.job4j.design.lsp.storage;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Milk extends Food {

    public Milk(String name, Calendar createDate, Calendar expiryDate, float price) {
        super(name, createDate, expiryDate, price);
    }

}
