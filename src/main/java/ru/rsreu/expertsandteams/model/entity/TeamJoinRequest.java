package ru.rsreu.expertsandteams.model.entity;

public class TeamJoinRequest {
    private Long id;
    private User user;
    private User captain;
    private String message;

    public TeamJoinRequest(User user, User captain, String message) {
        this.user = user;
        this.captain = captain;
        this.message = message;
    }

    public TeamJoinRequest(Long id, User user, User captain, String message) {
        this.id = id;
        this.user = user;
        this.captain = captain;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getCaptain() {
        return captain;
    }

    public void setCaptain(User captain) {
        this.captain = captain;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
