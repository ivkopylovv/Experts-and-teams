package ru.rsreu.expertsandteams.model.api.request;

public class GetChatRequest {
    private Long teamId;

    public GetChatRequest(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
