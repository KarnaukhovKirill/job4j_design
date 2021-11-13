package ru.job4j.design.isp.menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

/**
 * Класс Menu служит для генерации меню пользователя.
 * Пример вывода меню:
 * Задача 1.
 * ---- Задача 1.1.
 * --------- Задача 1.1.1.
 * --------- Задача 1.1.2.
 * ----- Задача 1.2.
 */
public class Menu implements Generatable<Item>, Printable {
    private TreeMap<String, Item> items;

    public Menu(Comparator<String> comparator) {
        this.items = new TreeMap<>(comparator);
    }

    /**
     * Создание списка меню.
     * @param list список меню в любом порядке
     */
    @Override
    public void generate(List<Item> list) {
        list.forEach(item -> items.put(item.getName(), item));
    }

    /**
     *
     * @return возврат списка меню в String
     */
    @Override
    public String print() {
        return String.join(System.lineSeparator(), items.keySet());
    }

    /**
     * Метод предназначен для вывода списка Item в порядке, заложенном в Comparator.
     * С таким списком, в дальнейшем, будет удобно работать. Доступ к Item по index.
     * @return выводит список всех Item
     */
    public List<Item> getItems() {
        return new ArrayList<>(items.values());
    }
}
