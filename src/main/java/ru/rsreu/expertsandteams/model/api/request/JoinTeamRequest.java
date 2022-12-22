package ru.rsreu.expertsandteams.model.api.request;

public class JoinTeamRequest {
    private Long teamId;
    private String message;

    public JoinTeamRequest(Long teamId, String message) {
        this.teamId = teamId;
        this.message = message;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
