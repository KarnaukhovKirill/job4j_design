package ru.job4j.cache;

import java.util.Scanner;

public class Emulator {
    private static AbstractCache cache;
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        new Emulator().init();
    }

    private void showMenu() {
        System.out.println("Select menu: " + "\n"
                + "1. Choose cache folder" + "\n"
                + "2. Load .txt file to cache" + "\n"
                + "3. Get info from cache" + "\n"
                + "4. Exit");
    }

    public void init() {
        boolean run = true;
        while (run) {
            showMenu();
            int select = Integer.parseInt(SCANNER.nextLine());
            if (select == 1) {
                System.out.println("Write folder path");
                String path = SCANNER.nextLine();
                cache = new DirFileCache(path);
            } else if (select == 2) {
                System.out.println("Write file name in format name.txt");
                String file = SCANNER.nextLine();
                cache.load(file);
            } else if (select == 3) {
                System.out.println("Write file name in format name.txt");
                String file = SCANNER.nextLine();
                var txt = (String) cache.get(file);
                System.out.println(txt);
            } else if (select < 0 || select > 3) {
                run = false;
                System.out.println("Good bye");
            }
        }
    }
}
