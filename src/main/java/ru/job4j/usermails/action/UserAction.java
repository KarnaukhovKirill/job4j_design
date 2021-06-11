package ru.job4j.usermails.action;

import ru.job4j.usermails.Input;
import ru.job4j.usermails.base.UsersBase;

public interface UserAction {
    String name();
    boolean execute(Input input, UsersBase usersBase);
}
