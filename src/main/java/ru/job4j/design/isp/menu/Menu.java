package ru.job4j.design.isp.menu;

import java.util.*;

/**
 * Класс Menu служит для генерации меню пользователя.
 * Пример вывода меню:
 * Задача 1.
 * ---- Задача 1.1.
 * --------- Задача 1.1.1.
 * --------- Задача 1.1.2.
 * ----- Задача 1.2.
 */
public class Menu {
    private List<Item> elements = new ArrayList<>();

    public Menu() {
    }

    public Menu(Item root) {
        elements.add(root);
    }

    /**
     * Метод добавляет подзадачу. Если нужна новая, глобальная задача, то в parentName можно вбить "Новая задача".
     * @param parentName имя главной задачи
     * @param childName имя подзадачи
     * @param action действие подзадачи
     * @return результат. true - подзадача добавилась. false - подзадача не добавилась
     */
    public boolean add(String parentName, String childName, Action action) {
        boolean result = false;
        Item item = find(parentName);
            if (item != null) {
                item.getChildren().add(new Item(childName, action));
                result = true;
            } else {
                elements.add(new Item(childName, action));
            }
            return result;
        }

    /**
     * Поиск задачи по имени.
      * @param parentName имя нужной задачи
     * @return Item
     */
    private Item find(String parentName) {
        Item result = null;
        Queue<Item> queue = new ArrayDeque<>(elements);
        while (!queue.isEmpty()) {
            Item item = queue.poll();
            if (item.getName().equals(parentName)) {
                result = item;
                break;
            }
            queue.addAll(item.getChildren());
        }
        return result;
    }

    /**
     * Метод вызывает Action у найденного Item
     * @param itemName имя задачи
     * @return действие задачи
     */
    public Action select(String itemName) {
        Action action;
        var item = this.find(itemName);
        if (item != null) {
            action = item.getAction();
        } else {
            throw new IllegalArgumentException("Такой задачи нет!");
        }
        return action;
    }

    /**
     * Эксперементальный метод. Сделан больше для теста, чтобы проверить items в коллекции elements.
     * @return дубликаты "рутовых" Item
     */
    public List<Item> getRoots() {
        List<Item> result = new ArrayList<>();
        for (Item item : elements) {
            try {
                result.add((Item) item.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}

