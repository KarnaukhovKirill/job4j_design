package ru.job4j.design.isp;

/**
 * Интерфейс People имеет слишком много методов. Реализация каждого из них займёт много время.
 */
public interface People {
    void walk();
    void run();
    void writeCode();
    void sleep();
    void eat();
    void play();
}
