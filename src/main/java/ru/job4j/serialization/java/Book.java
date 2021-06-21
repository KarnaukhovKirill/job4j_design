package ru.job4j.serialization.java;

public class Book {
    private final String name;
    private final int pages;

    public Book(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }

    public String getName() {
        return name;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return "Book{"
                + "name='" + name + '\''
                + ", pages=" + pages
                + '}';
    }
}
