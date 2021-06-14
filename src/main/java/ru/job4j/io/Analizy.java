package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Analizy {
    List<String> list = new ArrayList<>();
    private boolean flag = true;

    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source))) {
            String line;
            while ((line = in.readLine()) != null) {
                if ((line.startsWith("500") || line.startsWith("400")) && flag) {
                    flag = false;
                    list.add(line.split(" ")[1]);
                }
                if ((line.startsWith("200") || line.startsWith("300")) && !flag) {
                    flag = true;
                    list.add(line.split(" ")[1]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try (PrintWriter out = new PrintWriter(new FileOutputStream(target))) {
            int index = 0;
            for (String time : list) {
                if (index == 0) {
                    out.print(time + ";");
                    index++;
                } else if (index == 1) {
                    out.println(time);
                    index = 0;
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analizy analizy = new Analizy();
        analizy.unavailable("source.txt", "target.csv");
    }
}
