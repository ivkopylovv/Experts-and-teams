package ru.rsreu.expertsandteams.model.entity;

public class TeamJoinRequest {
    private Long id;
    private User user;
    private Team team;
    private String message;

    public TeamJoinRequest(Long id, User user, String message) {
        this.id = id;
        this.user = user;
        this.message = message;
    }

    public TeamJoinRequest(User user, Team team, String message) {
        this.user = user;
        this.team = team;
        this.message = message;
    }

    public TeamJoinRequest(Long id, User user, Team team, String message) {
        this.id = id;
        this.user = user;
        this.team = team;
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

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
