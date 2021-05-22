package ru.job4j.collection.set;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

    @Test
    public void whenAddStringAndCheckContains() {
        Set<String> set = new SimpleSet<>();
        assertTrue(set.add("Hello"));
        assertTrue(set.contains("Hello"));
    }

    @Test
    public void whenAddIntegerAndCheckContains() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
    }

    @Test
    public void whenIteratorHasNextTrueAndThenFalse() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        Iterator<Integer> iterator = set.iterator();
        assertTrue(iterator.hasNext());
        iterator.next();
        assertFalse(iterator.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void whenThrowNoSuchElement() {
        Set<Integer> set = new SimpleSet<>();
        Iterator<Integer> iterator = set.iterator();
        iterator.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenThrowConcurrentMod() {
        Set<Integer> set = new SimpleSet<>();
        set.add(1);
        Iterator<Integer> iterator = set.iterator();
        set.add(2);
        iterator.hasNext();
    }
}