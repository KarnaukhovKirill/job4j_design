package ru.job4j.usermails;

public class ConsoleOutput implements Output {
    @Override
    public void println(Object obj) {
        System.out.println(obj);
    }
}
