package ru.job4j.design.isp;

import org.junit.Test;
import ru.job4j.design.isp.menu.*;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class MenuTest {
    String ln = System.lineSeparator();

    @Test
    public void addItems() {
        Action action = new AnyAction();
        Item root = new Item("Задача 1", action);
        Menu menu = new Menu(root);
        menu.add("Задача 1", "Задача 1.1", action);
        menu.add("Новая задача", "Задача 2", action);
        menu.add("Задача 1.1", "Задача 1.1.1", action);
        assertThat(root.getChildren(),
                is(List.of(new Item("Задача 1.1", action))));
        assertThat(root.getChildren().get(0).getChildren(),
                is(List.of(new Item("Задача 1.1.1", action))));
    }

    @Test
    public void addItemsAndGetRoots() {
        Action action = new AnyAction();
        Item root = new Item("Задача 1", action);
        Menu menu = new Menu(root);
        menu.add("Задача 1", "Задача 1.1", action);
        menu.add("Новая задача", "Задача 2", action);
        assertThat(menu.getRoots(),
                is(List.of(root, new Item("Задача 2", action))));
    }

    @Test
    public void addItemsGetAction() {
        Action action = new AnyAction();
        Item root = new Item("Задача 1", action);
        Menu menu = new Menu(root);
        menu.add("Задача 1", "Задача 1.1", action);
        menu.add("Новая задача", "Задача 2", action);
        var result = menu.select("Задача 2");
        assertThat(result, is(action));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addItemsAndNotFoundAction() {
        Action action = new AnyAction();
        Item root = new Item("Задача 1", action);
        Menu menu = new Menu(root);
        menu.add("Задача 1", "Задача 1.1", action);
        menu.add("Новая задача", "Задача 2", action);
        menu.select("Задача 100500");
    }
}
