package ru.job4j.usermails.action;

import ru.job4j.usermails.Input;
import ru.job4j.usermails.Output;
import ru.job4j.usermails.base.UsersBase;

public class Merge implements UserAction {
    private final Output out;

    public Merge(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Merging users";
    }

    @Override
    public boolean execute(Input input, UsersBase usersBase) {
        out.println("=== Merge users ===");
        usersBase.merge(usersBase.getUsersBase());
        return true;
    }
}
