package ru.rsreu.expertsandteams.data;

import java.util.Date;

public class Session {
    private Long userId;
    private Date expiredAt;

    public Session(Long userId, Date expiredAt) {
        this.userId = userId;
        this.expiredAt = expiredAt;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }
}
