package ru.job4j.serialization.java;

import org.json.JSONArray;
import org.json.JSONObject;


public class Main {
    public static void main(String[] args) throws Exception {
        Book[] books = new Book[] {new Book("East Asia at the Center", 150),
                new Book("1941-1945", 250)};
        BookShelf firstShelf = new BookShelf(true, Genre.HISTORY, books);
        JSONArray jsonBooks = new JSONArray(firstShelf.getBooks());  //jsonObject из массива
        System.out.println(jsonBooks);
        JSONObject jsonCountry = new JSONObject("{\"Russia\":\"Best country\"}");
        System.out.println(jsonCountry);
        JSONObject fullBookShelf = new JSONObject();
        fullBookShelf.put("isEmpty", firstShelf.isEmpty());
        fullBookShelf.put("Genre", firstShelf.getGenre());
        fullBookShelf.put("countPlace", firstShelf.getCountPlace());
        fullBookShelf.put("name", firstShelf.getName());
        fullBookShelf.put("books", jsonBooks);
        System.out.println(fullBookShelf);  //Созданная вручную строка json
        System.out.println(new JSONObject(firstShelf).toString()); //Преобразование объекта BookShelf в json строку
    }
}
