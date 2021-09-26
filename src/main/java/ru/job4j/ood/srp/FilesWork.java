package ru.job4j.ood.srp;

import java.util.List;

public interface FilesWork<T> {
    List<T> input(String fileName);
    List<T> order(List<T> inputList);
}
