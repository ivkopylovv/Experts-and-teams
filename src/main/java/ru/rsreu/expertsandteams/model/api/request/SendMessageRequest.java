package ru.rsreu.expertsandteams.model.api.request;

public class SendMessageRequest {
    private Long teamId;
    private String messageId;
    private Long expertId;

    public SendMessageRequest(Long teamId, String messageId, Long expertId) {
        this.teamId = teamId;
        this.messageId = messageId;
        this.expertId = expertId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }
}
