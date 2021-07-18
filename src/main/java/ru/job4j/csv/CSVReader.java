package ru.job4j.csv;

import ru.job4j.search.ArgsName;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.Consumer;

public class CSVReader {
    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException(
                    "Use syntax: java -jar target/csvReader.jar -path=file.csv -delimiter=\";\"  -out=stdout -filter=name,age");
        }
        var argsName = ArgsName.of(args);
        CheckInputArgs checkInputArgs = new CheckInputArgs(argsName);
        try {
            checkInputArgs.check();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        if (argsName.get("out").equals("stdout")) {
            csvToOut(argsName, System.out::print);
        } else {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(argsName.get("out")))) {
                csvToOut(argsName, s -> {
                    try {
                        bw.write(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static void csvToOut(ArgsName argsName, Consumer<String> consumer) {
        Path inputPath = Paths.get(argsName.get("path"));
        try (Scanner scanner = new Scanner(inputPath)) {
            List<Integer> indexOut = new ArrayList<>();
            var columnName = scanner.nextLine().split(argsName.get("delimiter"));
            var filterName = argsName.get("filter").split(",");
            for (var i = 0; i < columnName.length; i++) {
                for (var s : filterName) {
                    if (columnName[i].toLowerCase(Locale.ROOT).contains(s)) {
                        indexOut.add(i);
                        break;
                    }
                }
            }
            while (scanner.hasNextLine()) {
                var arrayLine = scanner.nextLine().split(argsName.get("delimiter"));
                for (var i : indexOut) {
                    consumer.accept(arrayLine[i]);
                }
                consumer.accept(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
