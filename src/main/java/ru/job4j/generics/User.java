package ru.job4j.generics;

public class User extends Base {
    public User(String id) {
        super(id);
    }

    @Override
    public int hashCode() {
        int code = 11;
        int k = 7;
        code = k * code + getId().hashCode();
        return code;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        User newUser = (User) obj;
        return getId().equals(newUser.getId());
    }
}
