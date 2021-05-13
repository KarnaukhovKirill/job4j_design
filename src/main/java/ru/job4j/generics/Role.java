package ru.job4j.generics;

public class Role extends Base {
    public Role(String id) {
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
        Role newUser = (Role) obj;
        return getId().equals(newUser.getId());
    }

    //comment for commit
}
