package ru.job4j.collection.map;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void testPut() {
        Map<String, Integer> myMap = new SimpleMap<>();
        myMap.put("First", 1);
        assertThat(myMap.get("First"), is(1));
    }

    @Test
    public void testPutSameObjects() {
        Map<String, Integer> myMap = new SimpleMap<>();
        myMap.put("First", 1);
        myMap.put("First", 1);
        assertThat(myMap.get("First"), is(1));
        myMap.remove("First");
        assertNull(myMap.get("First"));
    }

    @Test
    public void testGet() {
        Map<String, String> myMap = new SimpleMap<>();
        myMap.put("Moscow", "First");
        assertThat(myMap.get("Moscow"), is("First"));
        assertNull(myMap.get("Stefan"));
    }

    @Test
    public void testRemove() {
        Map<String, String> myMap = new SimpleMap<>();
        myMap.put("Lordi", "Hard Rock");
        assertTrue(myMap.remove("Lordi"));
        assertNull(myMap.get("Lordi"));
    }

    @Test
    public void testRemoveWithErrorKey() {
        Map<String, String> myMap = new SimpleMap<>();
        myMap.put("Lordi", "Hard Rock");
        assertFalse(myMap.remove("Lor"));
    }

    @Test
    public void testHasNext() {
        Map<String, String> myMap = new SimpleMap<>();
        myMap.put("Lol", "kek");
        Iterator<String> iterator = myMap.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test (expected = ConcurrentModificationException.class)
    public void testHasNextException() {
        Map<String, String> myMap = new SimpleMap<>();
        myMap.put("Lol", "kek");
        Iterator<String> iterator = myMap.iterator();
        assertTrue(iterator.hasNext());
        myMap.remove("Lol");
        iterator.hasNext();
    }
}