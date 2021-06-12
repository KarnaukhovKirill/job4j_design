package ru.job4j.usermails;

import ru.job4j.usermails.action.*;
import ru.job4j.usermails.base.UsersBase;
import java.util.ArrayList;
import java.util.List;

public class StartUI {
    private final Output out;

    public StartUI(Output out) {
        this.out = out;
    }

    public void init(Input input, UsersBase usersBase, List<UserAction> actions) {
        boolean run = true;
        while (run) {
            this.showMenu(actions);
            int select = input.askInt("Select: ");
            if (select < 0 || select >= actions.size()) {
                out.println("Wrong input, you can select: 0 . . " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            run = action.execute(input, usersBase);
        }
    }

    private void showMenu(List<UserAction> actions) {
        out.println("Menu");
        for (int index = 0; index < actions.size(); index++) {
            out.println(index + ". " + actions.get(index).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ConsoleInput();
        UsersBase uBase = new UsersBase();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateUser(output));
        actions.add(new AddEmail(output));
        actions.add(new ShowEmails(output));
        actions.add(new Merge(output));
        actions.add(new Exit(output));
        new StartUI(output).init(input, uBase, actions);
    }
}