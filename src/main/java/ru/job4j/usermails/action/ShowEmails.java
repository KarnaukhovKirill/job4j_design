package ru.job4j.usermails.action;

import ru.job4j.usermails.Input;
import ru.job4j.usermails.Output;
import ru.job4j.usermails.base.UsersBase;
import java.util.List;
import java.util.Map;

public class ShowEmails implements UserAction {
    private final Output out;

    public ShowEmails(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Show all Users";
    }

    @Override
    public boolean execute(Input input, UsersBase usersBase) {
        for (Map.Entry<String, List<String>> user : usersBase.getUsersBase().entrySet()) {
            System.out.println(user.getKey() + " -> " + user.getValue().toString());
        }
        return true;
    }
}
