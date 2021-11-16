package ru.job4j.design.isp.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Item implements Cloneable {
    private String name;
    private Action action;
    private List<Item> children = new ArrayList<>();

    public Item(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public List<Item> getChildren() {
        return children;
    }

    public void setChildren(List<Item> children) {
        this.children = children;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(name, item.name) && Objects.equals(action, item.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, action);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
