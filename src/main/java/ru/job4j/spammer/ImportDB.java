package ru.job4j.spammer;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private final Properties cfg;
    String dump;

    public ImportDB(Properties cfg, String dump) {
        this.cfg = cfg;
        this.dump = dump;
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader rd = new BufferedReader(new FileReader(dump))) {
            rd.lines()
                    .map(s -> s.split(";"))
                    .map(strings -> new User(strings[0], strings[1]))
                    .forEach(users::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException {
        Class.forName(cfg.getProperty("hibernate.connection.driver_class"));
        try (Connection cnt = DriverManager.getConnection(
                cfg.getProperty("hibernate.connection.url"),
                cfg.getProperty("hibernate.connection.username"),
                cfg.getProperty("hibernate.connection.password")
        )) {
            for (User user : users) {
                try (PreparedStatement ps = cnt.prepareStatement("insert into users(name, email) values (?, ?);")) {
                    ps.setString(1, user.name);
                    ps.setString(2, user.email);
                    ps.execute();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Properties cfg = new Properties();
        var file = new File("app.properties").getAbsolutePath();
        try (FileInputStream in = new FileInputStream(file)) {
            cfg.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        var fileOutput = new File("dump.txt").getAbsolutePath();
        ImportDB db = new ImportDB(cfg, fileOutput);
        try {
            db.save(db.load());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
