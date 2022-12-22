package ru.rsreu.expertsandteams.model.entity;

import java.util.List;

public class Team {
    private Long id;
    private String name;
    private Long membersCount;
    private User captain;
    private List<User> members;

    public Team(Long id) {
        this.id = id;
    }

    public Team(String name, User captain) {
        this.name = name;
        this.captain = captain;
    }

    public Team(Long id, String name, Long membersCount) {
        this.id = id;
        this.name = name;
        this.membersCount = membersCount;
    }

    public Team(String name, Long membersCount, User captain) {
        this.name = name;
        this.membersCount = membersCount;
        this.captain = captain;
    }

    public Team(Long id, String name, Long membersCount, User captain) {
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

    public Long getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Long membersCount) {
        this.membersCount = membersCount;
    }

    public User getCaptain() {
        return captain;
    }

    public void setCaptain(User captain) {
        this.captain = captain;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
