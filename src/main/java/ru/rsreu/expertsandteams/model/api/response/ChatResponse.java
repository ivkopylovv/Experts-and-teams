package ru.rsreu.expertsandteams.model.api.response;

import java.util.List;

public class ChatResponse {
    private String teamName;
    private List<MessageResponse> messages;

    public ChatResponse(String teamName, List<MessageResponse> messages) {
        this.teamName = teamName;
        this.messages = messages;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public List<MessageResponse> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageResponse> messages) {
        this.messages = messages;
    }
}
