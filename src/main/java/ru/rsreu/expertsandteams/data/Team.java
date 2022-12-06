package ru.rsreu.expertsandteams.data;

public class Team {
    private Long id;
    private String name;
    private Integer membersCount;
    private Long captainId;

    public Team(Long id, String name, Integer membersCount, Long captainId) {
        this.id = id;
        this.name = name;
        this.membersCount = membersCount;
        this.captainId = captainId;
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

    public Long getCaptainId() {
        return captainId;
    }

    public void setCaptainId(Long captainId) {
        this.captainId = captainId;
    }
}
