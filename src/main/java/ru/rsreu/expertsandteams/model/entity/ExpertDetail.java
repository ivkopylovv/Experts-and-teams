package ru.rsreu.expertsandteams.model.entity;

public class ExpertDetail {
    private User expert;
    private Long currentTeamsCount;
    private Long maxTeamsCount;

    public ExpertDetail(User expert) {
        this.expert = expert;
    }

    public ExpertDetail(User expert, Long currentTeamsCount, Long maxTeamsCount) {
        this.expert = expert;
        this.currentTeamsCount = currentTeamsCount;
        this.maxTeamsCount = maxTeamsCount;
    }

    public User getExpert() {
        return expert;
    }

    public void setExpert(User expert) {
        this.expert = expert;
    }

    public Long getCurrentTeamsCount() {
        return currentTeamsCount;
    }

    public void setCurrentTeamsCount(Long currentTeamsCount) {
        this.currentTeamsCount = currentTeamsCount;
    }

    public Long getMaxTeamsCount() {
        return maxTeamsCount;
    }

    public void setMaxTeamsCount(Long maxTeamsCount) {
        this.maxTeamsCount = maxTeamsCount;
    }
}
