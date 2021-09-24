package ru.job4j.kiss;

import junit.framework.TestCase;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMinTest extends TestCase {

    public void testMax() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(3);
        integers.add(-10);
        integers.add(111);
        integers.add(-1);
        integers.add(50);
        MaxMin maxMin = new MaxMin();
        var max = maxMin.max(integers, Comparator.comparingInt(o -> o));
        assertThat(111, is(max));
    }

    public void testMin() {
        List<Integer> integers = new ArrayList<>();
        integers.add(1);
        integers.add(3);
        integers.add(-10);
        integers.add(111);
        integers.add(-1);
        integers.add(50);
        MaxMin maxMin = new MaxMin();
        var min = maxMin.min(integers, Comparator.comparingInt(o -> o));
        assertThat(-10, is(min));
    }

    public void testMinWord() {
        List<String> words = new ArrayList<>();
        words.add("A");
        words.add("B");
        words.add("Z");
        MaxMin maxMin = new MaxMin();
        var min = maxMin.min(words, Comparator.naturalOrder());
        assertThat("A", is(min));
    }
}