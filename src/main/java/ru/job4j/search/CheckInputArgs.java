package ru.job4j.search;

import java.io.File;
import java.nio.file.Path;
import java.util.Locale;

public class CheckInputArgs {
    public ArgsName argsName;

    public CheckInputArgs(ArgsName argsName) {
        this.argsName = argsName;
    }

    public boolean check() {
        Path startPath = Path.of(argsName.get("d"));
        if (!startPath.toFile().isDirectory()) {
            throw new IllegalArgumentException("Root is not directory. Example: C:/, D:/pictures/summer2020/");
        }
        String name = argsName.get("n");
        if (!name.contains(".") && !name.contains("*")) {
            throw new IllegalArgumentException("Name is invalid. Example: *.txt, fullname.exe");
        }
        String type = argsName.get("t").toLowerCase(Locale.ROOT);
        return (type.equals("mask") || type.equals("name") || type.equals("regex"));
    }
}
