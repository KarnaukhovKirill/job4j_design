package ru.job4j.it;

import org.hamcrest.core.Is;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

public class ListUtilsTest {

    @Test
    public void testAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(Arrays.asList(1, 2, 3), Is.is(input));
    }

    @Test
    public void testAddAfter() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 4, 5));
        ListUtils.addAfter(input, 1, 3);
        assertThat(Arrays.asList(1, 2, 3, 4, 5), Is.is(input));
    }

    @Test
    public void testRemoveIf() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListUtils.removeIf(input, i -> i < 4);
        assertThat(Arrays.asList(4, 5), Is.is(input));
    }

    @Test
    public void testReplaceIf() {
        List<String> input = new ArrayList<>(Arrays.asList("Moscow", "", "Berlin"));
        ListUtils.replaceIf(input, String::isEmpty, "Another City");
        assertThat(Arrays.asList("Moscow", "Another City", "Berlin"), Is.is(input));
    }

    @Test
    public void testReplaceAll() {
        List<Integer> input = new ArrayList<>(Arrays.asList(5, 5, 3, 4, 5));
        ListUtils.removeAll(input, List.of(1, 2, 5));
        assertThat(Arrays.asList(3, 4), Is.is(input));
    }
}