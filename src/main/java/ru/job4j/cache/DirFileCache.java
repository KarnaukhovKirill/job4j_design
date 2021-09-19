package ru.job4j.cache;

import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;

public class DirFileCache extends AbstractCache<String, String> {
    private final String cachingDir;
    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String rsl = null;
        Path dir = Path.of(cachingDir);
        Search searcher = new Search();
        try {
            var listFiles = searcher.search(dir, p -> p.toFile().getName().endsWith(key));
            if (listFiles.size() == 0) {
                rsl = "Cant find file";
            } else {
                rsl = getStringFromFile(listFiles.get(0));
                put(key, rsl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private String getStringFromFile(Path path) {
        StringBuilder text = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new FileReader(path.toString()))) {
            in.lines().forEach(s -> {
                text.append(s);
                text.append("\n");
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text.toString();
    }
}
