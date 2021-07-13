package ru.job4j.search;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class NameSearch {
    ArgsName argsName;

    public NameSearch(ArgsName argsName) {
        this.argsName = argsName;
    }

    public void search() {
        String name = argsName.get("n");
        SearchFiles searcher = new SearchFiles(path -> path.toFile().getName().endsWith(name));
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("o"))
                ))) {
            Files.walkFileTree(Paths.get(argsName.get("d")), searcher);
            searcher.getList().stream().map(path -> path.toFile().getName()).forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
