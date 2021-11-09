package ru.job4j.design.lsp.storage;

import java.util.Calendar;

public class Milk extends Food {

    public Milk(String name, Calendar createDate, Calendar expiryDate, float price) {
        super(name, createDate, expiryDate, price);
    }

}
