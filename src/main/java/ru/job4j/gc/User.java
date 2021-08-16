package ru.job4j.gc;

public class User {
    private int age;
    private String name;
    private boolean working;
    private char gender;

    public User() {
    }

    public User(int age, String name, boolean working, char gender) {
        this.age = age;
        this.name = name;
        this.working = working;
        this.gender = gender;
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.printf("Removed %d %s%n", age, name);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWorking() {
        return working;
    }

    public void setWorking(boolean working) {
        this.working = working;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
