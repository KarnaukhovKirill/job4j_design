package ru.job4j.design.lsp.storage;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class ControlQualityTest {
    ControlQuality<Storage<Food>> controlQuality;
    Storage<Food> shop;
    Storage<Food> warehouse;
    Storage<Food> trash;

    @Before
    public void init() {
        shop = new Shop<>();
        warehouse = new Warehouse<>();
        trash = new Trash<>();
        controlQuality = new ControlQuality<>(List.of(shop, warehouse, trash));
    }

    @Test
    public void whenAddFoodToWareHouse() {
        Milk goodMilk = new Milk("Good Milk",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 1),
                new GregorianCalendar(2025, Calendar.NOVEMBER, 31),
                60.00F);
        controlQuality.distribute(goodMilk);
        assertThat(warehouse.getFoods(), is(List.of(goodMilk)));
    }

    @Test
    public void whenAddFoodToTrash() {
        Milk badMilk = new Milk("Bad Milk",
                new GregorianCalendar(2018, Calendar.JANUARY, 1),
                new GregorianCalendar(2021, Calendar.NOVEMBER, 1),
                60.00F);
        controlQuality.distribute(badMilk);
        assertThat(trash.getFoods(), is(List.of(badMilk)));
    }

    @Test
    public void whenAddFoodToShop() {
        Grain buckwheat = new Grain("Grecha",
                new GregorianCalendar(2020, Calendar.AUGUST, 21),
                new GregorianCalendar(2024, Calendar.AUGUST, 12),
                150);
        controlQuality.distribute(buckwheat);
        assertThat(shop.getFoods(), is(List.of(buckwheat)));
    }

    @Test
    public void whenDiscountApply() {
        Grain buckwheat = new Grain("Grecha",
                new GregorianCalendar(2020, Calendar.AUGUST, 21),
                new GregorianCalendar(2022, Calendar.JANUARY, 1),
                150);
        assertThat(buckwheat.getDiscount(), is(0));
        controlQuality.distribute(buckwheat);
        assertThat(buckwheat.getDiscount(), is(30));
    }

    @Test
    public void whenRestore() {
        Grain buckwheat = new Grain("Grecha",
                new GregorianCalendar(2021, Calendar.AUGUST, 21),
                new GregorianCalendar(2025, Calendar.JANUARY, 1),
                150);
        Milk goodMilk = new Milk("Good Milk",
                new GregorianCalendar(2021, Calendar.NOVEMBER, 1),
                new GregorianCalendar(2025, Calendar.NOVEMBER, 31),
                60.00F);
        controlQuality.distribute(buckwheat);
        controlQuality.distribute(goodMilk);
        assertThat(warehouse.getFoods(), is(List.of(buckwheat, goodMilk)));
        buckwheat.setExpiryDate(new GregorianCalendar(2020, Calendar.DECEMBER, 31));
        goodMilk.setExpiryDate(new GregorianCalendar(2020, Calendar.DECEMBER, 31));
        controlQuality.resort();
        assertThat(trash.getFoods(), is(List.of(buckwheat, goodMilk)));
    }
}
