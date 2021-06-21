package ru.job4j.serialization.java;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Book")
public class Book {
    @XmlAttribute
    private String name;
    @XmlAttribute
    private int pages;

    public Book() {

    }

    public Book(String name, int pages) {
        this.name = name;
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "Book{"
                + "name='" + name + '\''
                + ", pages=" + pages
                + '}';
    }
}
