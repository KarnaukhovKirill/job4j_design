package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import ru.job4j.collection.Analise.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class AnaliseTest {

    @Test
    public void testDiffMain() {
        List<Analise.User> userList01 = new ArrayList<>(List.of(
                new Analise.User(1, "Kirill"),
                new Analise.User(2, "Petr"),
                new Analise.User(3, "Anastasiya")
        ));
        List<Analise.User> userList02 = new ArrayList<>(List.of(
                new Analise.User(1, "Kirill"),
                new Analise.User(4, "Veronica"),
                new Analise.User(3, "Nastya")
        ));
        Analise analise = new Analise();
        Info info = analise.diff(userList01, userList02);
        assertThat(info.added, is(1));
        assertThat(info.deleted, is(1));
        assertThat(info.changed, is(1));
    }

    @Test
    public void testDiffWhenPreviousIsEmpty() {
        List<Analise.User> userList01 = new ArrayList<>(List.of(

        ));
        List<Analise.User> userList02 = new ArrayList<>(List.of(
                new Analise.User(1, "Kirill"),
                new Analise.User(4, "Veronica"),
                new Analise.User(3, "Nastya")
        ));
        Analise analise = new Analise();
        Info info = analise.diff(userList01, userList02);
        assertThat(info.added, is(3));
        assertThat(info.deleted, is(0));
        assertThat(info.changed, is(0));
    }

    @Test
    public void testDiffWhenPreviousBigger() {
        List<Analise.User> userList01 = new ArrayList<>(List.of(
                new Analise.User(1, "Kirill"),
                new Analise.User(2, "Kirill"),
                new Analise.User(3, "Kirill")
        ));
        List<Analise.User> userList02 = new ArrayList<>(List.of(
                new Analise.User(1, "Kirya")
        ));
        Analise analise = new Analise();
        Info info = analise.diff(userList01, userList02);
        assertThat(info.added, is(0));
        assertThat(info.deleted, is(2));
        assertThat(info.changed, is(1));
    }

    @Test
    public void testDiffWhenPreviousIsRepited() {
        List<Analise.User> userList01 = new ArrayList<>(List.of(
                new Analise.User(1, "Kirill"),
                new Analise.User(1, "Kirill"),
                new Analise.User(1, "Kirill")
        ));
        List<Analise.User> userList02 = new ArrayList<>(List.of(
                new Analise.User(1, "Kirya"),
                new Analise.User(2, "Gleb"),
                new Analise.User(3, "Maria")
        ));
        Analise analise = new Analise();
        Info info = analise.diff(userList01, userList02);
        assertThat(info.added, is(2));
        assertThat(info.deleted, is(0));
        assertThat(info.changed, is(1));
    }

    @Test
    public void testDiffWhenSameLists() {
        List<Analise.User> userList01 = new ArrayList<>(List.of(
                new Analise.User(1, "Kirill"),
                new Analise.User(2, "Gleb"),
                new Analise.User(3, "Maria")
        ));
        List<Analise.User> userList02 = new ArrayList<>(List.of(
                new Analise.User(1, "Kirill"),
                new Analise.User(2, "Gleb"),
                new Analise.User(3, "Maria")
        ));
        Analise analise = new Analise();
        Info info = analise.diff(userList01, userList02);
        assertThat(info.added, is(0));
        assertThat(info.deleted, is(0));
        assertThat(info.changed, is(0));
    }
}