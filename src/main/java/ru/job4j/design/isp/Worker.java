package ru.job4j.design.isp;

/**
 * Интерфейс Worker описывает абстрактного работника в кафе.
 * Но администратор не готовит и будет не уместно реализовывать метод cook().
 * Повар не работает напрямую с клиентами и будут реализовывать только метод cook(). И т.д.
 * Интерфейс является избыточным.
 * Решением данной проблемы - разделить интерфейс на несколько.
 */
public interface Worker {
    void cook();
    void meetCustomer();
    void serve();
    void payment();
}

class Cook implements Worker {
    @Override
    public void cook() {
        System.out.println("Готовлю...");
    }

    @Override
    public void meetCustomer() {
        System.out.println("Не встречаю гостей");
    }

    @Override
    public void serve() {
        System.out.println("Не обслуживаю клиентов");
    }

    @Override
    public void payment() {
        System.out.println("Не принимаю оплату на кассе");
    }
}
