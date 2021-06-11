package ru.job4j.usermails.action;

import ru.job4j.usermails.Input;
import ru.job4j.usermails.Output;
import ru.job4j.usermails.base.UsersBase;

public class Exit implements UserAction {
    private final Output out;

    public Exit(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Exit program";
    }

    @Override
    public boolean execute(Input input, UsersBase usersBase) {
        out.println("Good bye");
        return false;
    }
}
