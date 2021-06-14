package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(String path) {
        this.path = path;
    }

    public void load() {  //считать все ключи в карту values
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            in.lines()
                    .filter(s -> !s.startsWith("#") && s.contains("="))
                    .map(s -> s.trim().split("="))
                    .filter(s -> {
                        if (s.length <= 1) {
                            throw new IllegalArgumentException("Value is empty");
                        }
                        return true;
                    })
                    .forEach(s -> values.put(s[0], s[1]));
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        Optional<String> rsl = Optional.ofNullable(values.get(key));
        return rsl.orElse("Nothing");
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("app.properties"));
    }
}
