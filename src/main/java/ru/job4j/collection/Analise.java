package ru.job4j.collection;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Analise {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info();
        Map<Integer, String> previousMap = previous.stream()
                .collect(Collectors.toMap(User::getId, User::getName, (x1, x2) -> x1));
        for (User user : current) {
            if (previousMap.containsKey(user.id) && !previousMap.get(user.id).equals(user.name)) {
                info.changed++;
            }
            if (!previousMap.containsKey(user.id)) {
                info.added++;
            }
        }
        info.deleted = previousMap.size() - current.size() + info.added;
        return info;
    }

    public static class User {
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }

        int id;
        String name;

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "User "
                    + this.id
                    + " {"
                    + "Name = "
                    + this.name
                    + "}";
        }

        @Override
        public int hashCode() {
            int rsl = 17;
            rsl = 31 * rsl + id;
            rsl = 31 * rsl + name.hashCode();
            return rsl;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            }
            if (this.getClass() != obj.getClass()) {
                return false;
            }
            User newUser = (User) obj;
            return this.id == newUser.id
                    && this.name.equals(newUser.name);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
    }
}
