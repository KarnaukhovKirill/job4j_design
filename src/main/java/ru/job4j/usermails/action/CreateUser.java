package ru.job4j.usermails.action;

import ru.job4j.usermails.Input;
import ru.job4j.usermails.Output;
import ru.job4j.usermails.base.UsersBase;
import ru.job4j.usermails.base.User;

public class CreateUser implements UserAction {
    private final Output out;

    public CreateUser(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Add new User";
    }

    @Override
    public boolean execute(Input input, UsersBase usersBase) {
        out.println("=== Create a new User ===");
        String name = input.askStr("Enter name: ");
        User user = new User(name);
        usersBase.addUser(user);
        return true;
    }
}
