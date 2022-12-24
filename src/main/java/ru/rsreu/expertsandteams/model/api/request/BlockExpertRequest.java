package ru.rsreu.expertsandteams.model.api.request;

public class BlockExpertRequest {
    private Long expertId;
    private Long teamId;
    private Boolean previousBlockStatus;

    public BlockExpertRequest(Long expertId, Long teamId, Boolean previousBlockStatus) {
        this.expertId = expertId;
        this.teamId = teamId;
        this.previousBlockStatus = previousBlockStatus;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public Boolean getPreviousBlockStatus() {
        return previousBlockStatus;
    }

    public void setPreviousBlockStatus(Boolean previousBlockStatus) {
        this.previousBlockStatus = previousBlockStatus;
    }
}
