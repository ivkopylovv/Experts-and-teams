package ru.rsreu.expertsandteams.model.entity;

import java.util.Date;

public class LastMessageRequest {
    private Team team;
    private User user;
    private Date requestDate;

    public LastMessageRequest(Team team, User user, Date requestDate) {
        this.team = team;
        this.user = user;
        this.requestDate = requestDate;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
}
