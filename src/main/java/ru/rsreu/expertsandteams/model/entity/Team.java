package ru.rsreu.expertsandteams.model.entity;

public class Team {
    private Long id;
    private String name;
    private Integer membersCount;
    private User captain;

    public Team(Long id, String name, Integer membersCount, User captain) {
        this.id = id;
        this.name = name;
        this.membersCount = membersCount;
        this.captain = captain;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Integer membersCount) {
        this.membersCount = membersCount;
    }

    public User getCaptain() {
        return captain;
    }

    public void setCaptain(User captain) {
        this.captain = captain;
    }
}
