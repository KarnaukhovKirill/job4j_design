package ru.job4j.design.isp.menu;

import java.util.Comparator;

public class MenuComparator implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        if (o1.contains(o2)) {
            return o1.length() - o2.length();
        }
        return o1.compareTo(o2);
    }
}
