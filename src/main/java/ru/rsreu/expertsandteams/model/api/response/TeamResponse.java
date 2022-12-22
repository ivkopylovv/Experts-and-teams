package ru.rsreu.expertsandteams.model.api.response;

public class TeamResponse {
    private Long id;
    private String name;
    private Long membersCount;
    private Long captainId;

    public TeamResponse(Long id, String name, Long membersCount, Long captainId) {
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

    public Long getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Long membersCount) {
        this.membersCount = membersCount;
    }

    public Long getCaptainId() {
        return captainId;
    }

    public void setCaptainId(Long captainId) {
        this.captainId = captainId;
    }
}
