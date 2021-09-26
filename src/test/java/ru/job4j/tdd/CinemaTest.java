package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class CinemaTest {

    @Ignore
    @Test
    public void find() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Ignore
    @Test
    public void buy() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void add() {
        Session session = new Session3D();
        Cinema cinema = new Cinema3D();
        cinema.add(session);
        var sessions = cinema.find(session1 -> true);
        assertThat(sessions, is(Arrays.asList(new Session3D())));
    }

    @Ignore
    @Test(expected = Exception.class)
    public void buyWithWrongDate() {
        Session session = new Session3D();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(1899, 9, 11, 20, 15);
        assertThat(session, is(new Ticket3D()));
    }

    @Ignore
    @Test
    public void sessionIsNotFound() {
        Cinema cinema = new Cinema3D();
        cinema.add(new Session3D());
        List<Session> sessions = cinema.find(session -> false);
        assertTrue(sessions.isEmpty());
    }

    @Ignore
    @Test(expected = Exception.class)
    public void buySamePlace() {
        Account account = new AccountCinema();
        Cinema cinema = new Cinema3D();
        Calendar date = Calendar.getInstance();
        date.set(2020, 10, 10, 23, 00);
        Ticket ticket = cinema.buy(account, 1, 1, date);
        Ticket ticket01 = cinema.buy(account, 1, 1, date);
        assertThat(ticket, is(new Ticket3D()));
        assertThat(ticket01, is(new Ticket3D()));
    }
}