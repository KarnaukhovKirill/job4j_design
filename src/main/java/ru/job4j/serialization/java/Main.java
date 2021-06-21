package ru.job4j.serialization.java;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;


public class Main {
    public static void main(String[] args) throws Exception {
        Book[] books = new Book[] {new Book("East Asia at the Center", 150),
                new Book("1941-1945", 250)};
        BookShelf firstShelf = new BookShelf(true, Genre.HISTORY, books);
        JAXBContext context = JAXBContext.newInstance(BookShelf.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(firstShelf, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            BookShelf result = (BookShelf) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
