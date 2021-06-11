package ru.job4j.usermails.base;

import java.util.*;

public class User {
    private final String name;
    private List<String> emails;

    public User(String name) {
        this.name = name;
        this.emails = new ArrayList<>();
    }

    public User(String name, List<String> emails) {
        this.name = name;
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
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
}

