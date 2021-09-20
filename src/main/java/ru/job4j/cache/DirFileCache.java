package ru.job4j.cache;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;
    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String rsl = null;
        Path dir = Path.of(cachingDir, key);
        try {
            rsl = Files.readString(dir);
            put(key, rsl);
        } catch (NoSuchFileException fileE) {
            System.out.println("Check cache path and file name");
            System.out.printf("Cache path is -----%s----- \n", cachingDir);
            System.out.printf("File name is -----%s----- \n", key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }
}
