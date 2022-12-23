package ru.rsreu.expertsandteams.model.api.request;

public class SendMessageRequest {
    private Long teamId;
    private String message;
    private Long expertId;

    public SendMessageRequest(Long teamId, String message, Long expertId) {
        this.teamId = teamId;
        this.message = message;
        this.expertId = expertId;
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

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }
}
