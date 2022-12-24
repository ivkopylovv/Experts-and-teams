package ru.rsreu.expertsandteams.model.api.request;

public class AvailableExpertsRequest {
    private Long teamId;

    public AvailableExpertsRequest(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
