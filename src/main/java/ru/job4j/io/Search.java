package ru.job4j.io;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            throw new IllegalArgumentException("Usage java -jar dir.jar ROOT_FOLDER EXTENSION");
        }
        Path start = Paths.get(args[0]);
        String extension = args[1];
        Search search = new Search();
        search.search(start, p -> p.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPath();
    }

    private static class SearchFiles implements FileVisitor<Path> {
        private final List<Path> list = new ArrayList<>();
        private final Predicate<Path> predicate;

        public SearchFiles(Predicate<Path> condition) {
            this.predicate = condition;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (predicate.test(file)) {
                list.add(file);
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) {
            return FileVisitResult.CONTINUE;
        }

        public List<Path> getPath() {
            return this.list;
        }
    }
}
