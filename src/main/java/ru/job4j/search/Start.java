package ru.job4j.search;
import java.util.Locale;

public class Start {
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Use syntax: java -jar find.jar -d=DIRECTORY -n=FILENAME -t=MASK or NAME or REGEX -o=OUTPUT");
        }
        ArgsName argsName = ArgsName.of(args);
        CheckInputArgs checkArgs = new CheckInputArgs(argsName);
        try {
            checkArgs.check();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (args[2].toLowerCase(Locale.ROOT).endsWith("mask")) {
            MasksSearch search = new MasksSearch(argsName);
            search.search();
            System.out.println("По маске");
        }
        if (args[2].toLowerCase(Locale.ROOT).endsWith("name")) {
            NameSearch search = new NameSearch(argsName);
            search.search();
        }
    }
}
