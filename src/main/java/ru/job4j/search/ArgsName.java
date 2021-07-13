package ru.job4j.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> pair = new HashMap<>();

    public String get(String key) {
        if (!pair.containsKey(key)) {
            throw new IllegalArgumentException("Argument not found");
        }
        return pair.get(key);
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private void parse(String[] args) {
        Arrays.stream(args)
                .map(s -> s.split("="))
                .filter(strings -> {
                    if (strings.length != 2) {
                        throw new IllegalArgumentException("Аргументы пишуться в формате: -name=argument");
                    }
                    return true;
                })
                .forEach(strings -> pair.put(strings[0].substring(1), strings[1]));
    }
}
