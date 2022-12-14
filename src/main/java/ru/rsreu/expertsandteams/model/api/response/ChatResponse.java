package ru.rsreu.expertsandteams.model.api.response;

import java.util.List;

public class ChatResponse {
    private String teamName;
    private Long membersCount;
    private List<MessageResponse> messages;

    public ChatResponse(String teamName, Long membersCount, List<MessageResponse> messages) {
        this.teamName = teamName;
        this.membersCount = membersCount;
        this.messages = messages;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Long membersCount) {
        this.membersCount = membersCount;
    }

    public List<MessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageResponse> messages) {
        this.messages = messages;
    }
}
