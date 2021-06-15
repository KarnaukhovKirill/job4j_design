package ru.job4j.io;

import java.io.*;

public class Analizy {
    StringBuilder rsl = new StringBuilder();
    private boolean flag = true;

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = in.readLine()) != null) {
                if ((line.startsWith("500") || line.startsWith("400")) && flag) {
                    flag = false;
                    rsl.append(line.split(" ")[1]);
                    rsl.append(";");
                }
                if ((line.startsWith("200") || line.startsWith("300")) && !flag) {
                    flag = true;
                    rsl.append(line.split(" ")[1]);
                    rsl.append(System.lineSeparator());
                }
            }
            save(target);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void save(String target) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            out.println(rsl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("source.txt", "target.csv");
    }
}
