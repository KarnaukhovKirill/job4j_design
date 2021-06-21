package ru.job4j.serialization.java;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
        Book[] books = new Book[] {new Book("East Asia at the Center", 150)};
        BookShelf firstShelf = new BookShelf(true, Genre.HISTORY, books);
        final Gson gson = new GsonBuilder().create();
        try (FileOutputStream fos = new FileOutputStream("json.txt")) {
            fos.write(gson.toJson(firstShelf).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(gson.toJson(firstShelf));
        final String shelfJson = "{"
                                + "\"countPlace\":4,"
                                + "\"isEmpty\":true,"
                                + "\"name\":10B, "
                                + "\"genre\":HISTORY, "
                                  + "\"books\":"
                                        + "["
                                                + "{"
                                                + "\"name\":\"East Asia at the Center\", "
                                        + "\"pages\":150"
                                                + "}"
                                        + "]"
                                    + "}";
        final BookShelf shelfMod = gson.fromJson(shelfJson, firstShelf.getClass());
        System.out.println(shelfMod.toString());
    }
}
