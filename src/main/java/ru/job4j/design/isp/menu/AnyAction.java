package ru.job4j.design.isp.menu;

public class AnyAction implements Action {
    @Override
    public void run() {
        System.out.println("Do something");
    }
}
