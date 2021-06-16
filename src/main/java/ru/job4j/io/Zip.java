package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File file : sources) {
                zip.putNextEntry(new ZipEntry(file.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<File> searchFile(Path root, String extension) {
        List<File> fileList = new ArrayList<>();
        Search searcher = new Search();
        try {
            List<Path> pathList = searcher.search(root, path -> path.toFile().getName().endsWith(extension));
            fileList = pathList
                    .stream()
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Use syntax: java -jar pack.jar ARG1 ARG2 ARG3");
        }
        ArgsName argsName = ArgsName.of(args);
        checkInputArgs(argsName);
        List<File> fileList = searchFile(Path.of(argsName.get("d")), argsName.get("e"));
        new Zip().packFiles(fileList, new File(argsName.get("o")));
    }

    private static void checkInputArgs(ArgsName argsName) {
        File file = new File(argsName.get("o"));
        String[] nameFile = file.getName().split("\\.");
        if (nameFile.length < 2 || nameFile[1].length() < 1) {
            throw new IllegalArgumentException("FILE must be format: name.ext");
        }
        Path root = Paths.get(argsName.get("d"));
        String extension = argsName.get("e");
        if (root.toFile().getName().length() < 1) {
            throw new IllegalArgumentException("ROOT name is empty.");
        }
        if (!extension.startsWith(".")) {
            throw new IllegalArgumentException("EXTENSION must be .ext");
        }
    }
}
