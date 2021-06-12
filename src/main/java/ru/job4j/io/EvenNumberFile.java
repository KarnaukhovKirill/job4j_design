package ru.job4j.io;

import java.io.FileInputStream;
import java.util.stream.Stream;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] str = text.toString().split(System.lineSeparator());
            Stream.of(str)
                    .mapToInt(Integer::valueOf)
                    .forEach(i -> System.out.println(i % 2 == 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
