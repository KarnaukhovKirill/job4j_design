package ru.job4j.tree;

import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;


public class SimpleTreeTest {
    @Test
    public void when6ElFindLastThen6() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void whenTryToAddSameChild() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(tree.add(1, 2), is(false));
    }

    @Test
    public void whenChildNotExistThenFalse() {
        Tree<Integer> tree = new SimpleTree<>(100);
        tree.add(100, 99);
        assertTrue(tree.findBy(100).isPresent());
        assertFalse(tree.findBy(989).isPresent());
    }

    @Test
    public void whenNotBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);
        assertFalse(tree.isBinary());
    }

    @Test
    public void whenNotBinarySecond() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(4, 7);
        tree.add(4, 8);
        tree.add(8, 6);
        assertFalse(tree.isBinary());
    }

    @Test
    public void whenBinary() {
        Tree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(4, 5);
        tree.add(5, 6);
        assertTrue(tree.isBinary());
    }
}