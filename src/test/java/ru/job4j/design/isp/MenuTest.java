package ru.job4j.design.isp;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.isp.menu.*;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class MenuTest {
    Action action = new AnyAction();
    Menu menu;

    @Before
    public void init() {
        this.menu = new Menu();
    }

    @Test
    public void addItems() {
        menu.add("Новая задача", "Задача 1", action);
        menu.add("Задача 1", "Задача 1.1", action);
        menu.add("Новая задача", "Задача 2", action);
        menu.add("Задача 1.1", "Задача 1.1.1", action);
        assertThat(menu.getRoots(),
                is(List.of(new Item("Задача 1", action),
                        new Item("Задача 2", action))));
    }

    @Test
    public void addItemsAndGetRoots() {
        menu.add("Новая задача", "Задача 1", action);
        menu.add("Задача 1", "Задача 1.1", action);
        menu.add("Задача 1.1", "Задача 1.1.1", action);
        assertThat(menu.getRoots().get(0).getChildren().get(0),
                is(new Item("Задача 1.1", action)));
    }

    @Test
    public void addItemsGetAction() {
        menu.add("Новая задача", "Задача 1", action);
        menu.add("Задача 1", "Задача 1.1", action);
        menu.add("Новая задача", "Задача 2", action);
        var result = menu.select("Задача 2");
        assertThat(result, is(action));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addItemsAndNotFoundAction() {
        menu.add("Задача 1", "Задача 1.1", action);
        menu.add("Новая задача", "Задача 2", action);
        menu.select("Задача 100500");
    }
}
