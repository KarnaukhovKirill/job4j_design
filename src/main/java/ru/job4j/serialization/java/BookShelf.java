package ru.job4j.serialization.java;

import java.util.Arrays;

public class BookShelf {
    private final int countPlace = 4;
    private boolean isEmpty;
    private final String name = "10B";
    private final Genre genre;
    private Book[] books;

    public BookShelf(boolean isEmpty, Genre genre, Book[] books) {
        this.isEmpty = isEmpty;
        this.genre = genre;
        this.books = books;
    }

    public int getCountPlace() {
        return countPlace;
    }

    public boolean isEmpty() {
        return isEmpty;
    }

    public void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Book[] getBooks() {
        return books;
    }

    public void setBooks(Book[] books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "BookShelf{"
                + "countPlace=" + countPlace
                + ", isEmpty=" + isEmpty
                + ", name='" + name + '\''
                + ", genre=" + genre
                + ", books=" + Arrays.toString(books)
                + '}';
    }
}
