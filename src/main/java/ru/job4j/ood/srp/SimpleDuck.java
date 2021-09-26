package ru.job4j.ood.srp;

public class SimpleDuck implements Duck {
    @Override
    public void voice() {
        System.out.println("Кря");
    }

    @Override
    public void run() {
        System.out.println("Так бежит обычная утка");
    }

    @Override
    public void fly() {
        System.out.println("Так бежит обычная утка");
    }
}
