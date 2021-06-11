package ru.job4j.listemails;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    final private String name;
    private List<String> emails;

    public User(String name) {
        this.name = name;
        emails = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(getName(), user.getName()) && Objects.equals(getEmails(), user.getEmails());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getEmails());
    }

    public String getName() {
        return name;
    }

    public List<String> getEmails() {
        return emails;
    }
}
