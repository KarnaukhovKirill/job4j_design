package ru.job4j.usermails.base;

import java.util.*;
import java.util.stream.Collectors;

public class UsersBase {
    private Map<String, List<String>> usersBase = new HashMap<>();

    public Map<String, List<String>> getUsersBase() {
        return usersBase;
    }

    public User addUser(User user) {
        usersBase.put(user.getName(), user.getEmails());
        return user;
    }

    public boolean addMail(String name, String mail) {
        if (usersBase.containsKey(name)) {
            usersBase.get(name).add(mail);
            return true;
        }
        return false;
    }

    public boolean replaceUser(String oldName, String newName) {
        if (usersBase.containsKey(oldName)) {
            List<String> list = usersBase.remove(oldName);
            usersBase.put(newName, list);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String name) {
        if (usersBase.containsKey(name)) {
            usersBase.remove(name);
            return true;
        }
        return false;
    }

    public Map<String, List<String>> merge(Map<String, List<String>> input) {
        Map<String, List<String>> output = new HashMap<>();
        for (Map.Entry<String, List<String>> map : input.entrySet()) {
            String key = map.getKey();
            List<String> value = map.getValue();
            if (output.isEmpty()) {
                output.put(key, value);
                continue;
            }
            for (Map.Entry<String, List<String>> map2 : output.entrySet()) {
                if (!Collections.disjoint(value, map2.getValue())) {
                    Set<String> set = new HashSet<>(value);
                    set.addAll(map2.getValue());
                    List<String> end = new ArrayList<>(set);
                    map2.getValue().clear();
                    map2.getValue().addAll(end);
                } else {
                    output.put(key, value);
                    break;
                }
            }
        }
        return output;
    }
}