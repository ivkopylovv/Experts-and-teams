package ru.rsreu.expertsandteams.model.api.request;

public class TeamExpertsRequest {
    private Long teamId;

    public TeamExpertsRequest(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
