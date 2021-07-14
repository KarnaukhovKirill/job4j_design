package ru.job4j.search;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.function.Predicate;

public class Start {
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Use syntax: java -jar find.jar -d=DIRECTORY -n=FILENAME -t=MASK or NAME or REGEX -o=OUTPUT");
        }
        ArgsName argsName = ArgsName.of(args);
        CheckInputArgs checkArgs = new CheckInputArgs(argsName);
        try {
            checkArgs.check();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        var predicate = predicate(argsName);
        SearchFiles searchFiles = new SearchFiles(predicate);
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(argsName.get("o"))
                ))) {
            Files.walkFileTree(Paths.get(argsName.get("d")), searchFiles);
            searchFiles.getList().stream().map(path -> path.toFile().getName()).forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Predicate<Path> predicate(ArgsName argsName) {
        Predicate<Path> predicate = null;
        var typeSearch = argsName.get("t").toLowerCase(Locale.ROOT);
        var fileName = argsName.get("n").toLowerCase(Locale.ROOT);
        if (typeSearch.equals("mask")) {
            var array = fileName.split("\\.");
            var mask = array[1];
            predicate = path -> path.toFile().getName().endsWith(mask);
        } else if (typeSearch.equals("name")) {
            predicate = path -> path.toFile().getName().equals(fileName);
        }
        return predicate;
    }
}
