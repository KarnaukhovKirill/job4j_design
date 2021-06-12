package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public static List<String> filter(String file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            List<String> rsl = new ArrayList<>();
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] array = line.split(" ");
                if (array[array.length - 2].equals("404")) {
                    rsl.add(line);
                }
            }
            return rsl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        List<String> log = filter("log.txt");
        System.out.println(log);
    }
}
