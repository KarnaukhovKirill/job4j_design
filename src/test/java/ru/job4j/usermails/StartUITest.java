package ru.job4j.usermails;

import org.junit.Test;
import ru.job4j.listemails.User;
import ru.job4j.usermails.action.CreateUser;
import ru.job4j.usermails.action.Exit;
import ru.job4j.usermails.action.ShowEmails;
import ru.job4j.usermails.action.UserAction;
import ru.job4j.usermails.base.UsersBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

public class StartUITest {
    private final String ln = System.lineSeparator();

    @Test
    public void whenCreateItem() {
        Output output = new StubOutput();
        Input in = new StubInput(
                new String[] {"0", "user1", "2"}
        );
        UsersBase uBase = new UsersBase();
        List<UserAction> actions = new ArrayList<>();
        actions.add(new CreateUser(output));
        actions.add(new ShowEmails(output));
        actions.add(new Exit(output));
        new StartUI(output).init(in, uBase, actions);
        assertTrue(uBase.getUsersBase().containsKey("user1"));
    }


    @Test
    public void whenLikeInTask() {
        User user1 = new User("user1");
        user1.getEmails().add("xxx@ya.ru");
        user1.getEmails().add("foo@gmail.com");
        user1.getEmails().add("lol@mail.ru");
        User user2 = new User("user2");
        user2.getEmails().add("foo@gmail.com");
        user2.getEmails().add("ups@pisem.net");
        User user3 = new User("user3");
        user3.getEmails().add("xyz@pisem.net");
        user3.getEmails().add("vasya@pupkin.com");
        User user4 = new User("user4");
        user4.getEmails().add("ups@pisem.net");
        user4.getEmails().add("aaa@bbb.ru");
        User user5 = new User("user5");
        user5.getEmails().add("xyz@pisem.net");
        Map<String, List<String>> maps = new HashMap<>();
        maps.put(user1.getName(), user1.getEmails());
        maps.put(user2.getName(), user2.getEmails());
        maps.put(user3.getName(), user3.getEmails());
        maps.put(user4.getName(), user4.getEmails());
        maps.put(user5.getName(), user5.getEmails());
        UsersBase usersBase = new UsersBase();
        Map<String, List<String>> finalize = usersBase.merge(maps);
        assertThat(finalize.toString(), is("{user1=[aaa@bbb.ru, ups@pisem.net, "
                + "lol@mail.ru, xxx@ya.ru, "
                + "foo@gmail.com], "
                + "user5=[vasya@pupkin.com, xyz@pisem.net]}"));
    }
}