package ru.rsreu.expertsandteams.model.api.request;

public class JoinTeamDecisionRequest {
    private Long id;
    private Boolean isAccepted;

    public JoinTeamDecisionRequest(Long id, Boolean isAccepted) {
        this.id = id;
        this.isAccepted = isAccepted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(Boolean accepted) {
        isAccepted = accepted;
    }
}
