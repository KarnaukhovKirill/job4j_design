package ru.job4j.design.lsp.storage;

import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;

public class Food {
    TimeZone timeZone = TimeZone.getDefault();
    private String name;
    private Calendar expiryDate;
    private Calendar createDate;
    private float price;
    private int discount;

    public Food(String name, Calendar createDate, Calendar expiryDate, float price) {
        this.name = name;
        createDate.setTimeZone(timeZone);
        expiryDate.setTimeZone(timeZone);
        this.expiryDate = expiryDate;
        this.createDate = createDate;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Calendar getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Calendar expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Calendar getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Calendar createDate) {
        this.createDate = createDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Food food = (Food) o;
        return Float.compare(food.price, price) == 0 && Objects.equals(name, food.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}
