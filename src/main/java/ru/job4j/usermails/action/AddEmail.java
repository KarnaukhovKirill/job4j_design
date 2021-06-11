package ru.job4j.usermails.action;

import ru.job4j.usermails.Input;
import ru.job4j.usermails.Output;
import ru.job4j.usermails.base.UsersBase;

public class AddEmail implements UserAction {
    private final Output out;

    public AddEmail(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add New Email";
    }

    @Override
    public boolean execute(Input input, UsersBase usersBase) {
        String email = input.askStr("Enter email: ");
        String name = input.askStr("Enter name user: ");
        usersBase.addMail(name, email);
        return true;
    }
}
