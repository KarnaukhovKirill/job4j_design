package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesFinder {

    public static void main(String[] args) throws IOException {
        DuplicateSearcher searcher = new DuplicateSearcher();
        Files.walkFileTree(Path.of("./"), searcher);
    }


    private static class DuplicateSearcher extends SimpleFileVisitor<Path> {
        private final Set<FileProperty> files = new HashSet<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            FileProperty fileProperty = new FileProperty((int) file.toFile().length(),
                                                                            file.toFile().getName());
            if (files.contains(fileProperty)) {
                System.out.println(file.toAbsolutePath());
            } else {
                files.add(fileProperty);
            }
            return super.visitFile(file, attrs);
        }
    }
}
