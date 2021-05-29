package ru.job4j.tree;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public interface Tree<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    boolean isBinary();

    class Node<E> {
        final E value;
        final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }

        @Override
        public int hashCode() {
            int rsl = 17;
            rsl = rsl * 31 + value.hashCode();
            rsl = rsl * 31 + children.hashCode();
            return rsl;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            Node<E> temp = (Node<E>) obj;
            return Objects.equals(this.value, temp.value)
                    && this.children.equals(temp.children);
        }
    }
}
