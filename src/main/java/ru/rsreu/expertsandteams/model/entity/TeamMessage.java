package ru.rsreu.expertsandteams.model.entity;

import java.util.Date;

public class TeamMessage {
    private Long id;
    private Team team;
    private User user;
    private String message;
    private Date messageDate;
    private User expert;

    public TeamMessage(Team team, User user, String message, User expert) {
        this.team = team;
        this.user = user;
        this.message = message;
        this.expert = expert;
    }

    public TeamMessage(Team team, User user, String message, Date messageDate, User expert) {
        this.team = team;
        this.user = user;
        this.message = message;
        this.messageDate = messageDate;
        this.expert = expert;
    }

    public TeamMessage(Long id, Team team, User user, String message, Date messageDate, User expert) {
        this.id = id;
        this.team = team;
        this.user = user;
        this.message = message;
        this.messageDate = messageDate;
        this.expert = expert;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public User getExpert() {
        return expert;
    }

    public void setExpert(User expert) {
        this.expert = expert;
    }
}
