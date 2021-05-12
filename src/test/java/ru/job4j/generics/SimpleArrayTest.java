package ru.job4j.generics;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SimpleArrayTest {

    @Test
    public void testAdd() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(1);
        array.add(2);
        array.add(999);
        assertThat(array.get(0), is(1));
        assertThat(array.get(2), is(999));
    }

    @Test
    public void testGet() {
        SimpleArray<Integer> array = new SimpleArray<>(5);
        array.add(1);
        assertThat(array.get(0), is(1));
    }

    @Test
    public void testRemove() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("First");
        array.add("Second");
        array.add("Third");
        assertThat(array.get(1), is("Second"));
        array.remove(1);
        assertThat(array.get(1), is("Third"));
    }

    @Test
    public void testSet() {
        SimpleArray<String> array = new SimpleArray<>(5);
        array.add("Nick");
        array.set(0, "Prohor");
        assertThat(array.get(0), is("Prohor"));
    }

    @Test
    public void testIterator() {
        SimpleArray<String> array = new SimpleArray<>(1);
        Iterator<String> iterator = array.iterator();
        assertThat(iterator.hasNext(), is(false));
        array.add("First and Last");
        assertThat(iterator.hasNext(), is(true));
    }

    @Test
    public void testIteratorByForEach() {
        SimpleArray<String> array = new SimpleArray<>(3);
        Iterator<String> iterator = array.iterator();
        array.add("I");
        array.add("love");
        array.add("you");
        assertThat(iterator.next(), is("I"));
        assertTrue(iterator.hasNext());
        assertThat(iterator.next(), is("love"));
    }
}