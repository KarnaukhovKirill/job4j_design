package ru.job4j.design.isp;

/**
 * Интерфейс кнопки, которая имеет методы в ждущем режиме и при нажатии
 */
public interface Button {
    void sleep();
    void push();
}

/**
 * Класс любой кнопки, которая теперь должна реализовывать оба метода, хотя работает только при нажатии
 */
class AnyButton implements Button {
    @Override
    public void sleep() {
        System.out.println("Dont do anything");
    }

    @Override
    public void push() {
        System.out.println("Badaboom!");
    }
}
