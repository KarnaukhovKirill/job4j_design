package ru.job4j.io;

import java.io.FileOutputStream;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream out = new FileOutputStream("result.txt")) {
            for (int i = 1; i <= 9; i++) {
                if (i != 1) {
                    out.write(System.lineSeparator().getBytes());
                }
                for (int j = 1; j <= 9; j++) {
                    String result = Integer.toString(i * j);
                    String end = result + " ";
                    out.write(end.getBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
