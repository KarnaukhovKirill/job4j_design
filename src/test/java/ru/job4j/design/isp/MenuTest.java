package ru.job4j.design.isp;

import org.junit.Test;
import ru.job4j.design.isp.menu.*;
import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;

public class MenuTest {
    String ln = System.lineSeparator();

    @Test
    public void addItemsAndPrint() {
        Menu menu = new Menu(new MenuComparator());
        Action action = new AnyAction();
        Item root = new Item("Задача 1", action);
        Item first = new Item("Задача 1.1", action);
        Item second = new Item("Задача 2", action);
        menu.generate(List.of(first, second, root));
        assertThat(menu.print(), is(root.getName() + ln
                                        + first.getName() + ln
                                        + second.getName()));
    }

    @Test
    public void addItems() {
        Menu menu = new Menu(new MenuComparator());
        Action action = new AnyAction();
        Item root = new Item("Задача 1", action);
        Item first = new Item("Задача 1.1", action);
        Item second = new Item("Задача 2", action);
        menu.generate(List.of(first, root, second));
        assertThat(menu.getItems(), is(List.of(root, first, second)));
    }
}
