package ru.rsreu.expertsandteams.model.entity;

import java.util.Date;

public class Session {
    private Date expiredAt;
    private User user;

    public Session(User user, Date expiredAt) {
        this.user = user;
        this.expiredAt = expiredAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
