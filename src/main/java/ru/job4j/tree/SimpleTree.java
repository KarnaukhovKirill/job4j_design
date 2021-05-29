package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

class SimpleTree<E> implements Tree<E> {
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> parentNode = findBy(parent);
        Node<E> newChild = new Node<>(child);
        if (parentNode.isPresent()) {
            List<Node<E>> listParent = parentNode.get().children;
            if (listParent.isEmpty()) {
                listParent.add(newChild);
                return true;
            }
            if (!listParent.contains(newChild)) {
                listParent.add(newChild);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(eNode -> eNode.value.equals(value));
    }

    @Override
    public boolean isBinary() {
        Optional<Node<E>> rsl = findByPredicate(x -> x.children.size() > 2);
        return rsl.isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}
