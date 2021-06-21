package ru.job4j.serialization.java;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "bookshelf")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookShelf {
    @XmlAttribute
    private int countPlace = 4;
    @XmlAttribute
    private boolean isEmpty;
    @XmlAttribute
    private String name = "10B";
    @XmlAttribute
    private Genre genre;
    @XmlElementWrapper(name = "books")
    @XmlElement(name = "book")
    private Book[] books;

    public BookShelf() {
    }

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

    public String getName() {
        return name;
    }

    public Genre getGenre() {
        return genre;
    }

    public Book[] getBooks() {
        return books;
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
