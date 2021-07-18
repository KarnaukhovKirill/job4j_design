package ru.job4j.csv;

import ru.job4j.search.ArgsName;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CheckInputArgs {
    ArgsName argsName;

    public CheckInputArgs(ArgsName argsName) {
        this.argsName = argsName;
    }

    public void check() {
        Path inPath = Paths.get(argsName.get("path"));
        if (!inPath.toFile().isFile()) {
            throw new IllegalArgumentException("Wrong file path. Use absolute path. Example: C:\\projects\\file.csv");
        }
    }
}
