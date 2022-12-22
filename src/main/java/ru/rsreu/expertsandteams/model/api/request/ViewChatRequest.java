package ru.rsreu.expertsandteams.model.api.request;

public class ViewChatRequest {
    private Long teamId;

    public ViewChatRequest(Long teamId) {
        this.teamId = teamId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }
}
