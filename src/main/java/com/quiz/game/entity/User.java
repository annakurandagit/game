package com.quiz.game.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String userName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        return userName != null ? userName.equals(user.userName) : user.userName == null;
    }

    @Override
    public int hashCode() {
        return userName != null ? userName.hashCode() : 0;
    }
}
