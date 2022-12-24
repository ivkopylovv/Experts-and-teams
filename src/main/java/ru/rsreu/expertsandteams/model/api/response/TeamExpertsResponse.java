package ru.rsreu.expertsandteams.model.api.response;

import java.util.List;

public class TeamExpertsResponse {
    private Long expertId;
    private String expertName;
    private Boolean isBlocked;
    private List<String> skills;

    public TeamExpertsResponse(Long expertId, String expertName, Boolean isBlocked, List<String> skills) {
        this.expertId = expertId;
        this.expertName = expertName;
        this.isBlocked = isBlocked;
        this.skills = skills;
    }

    public Long getExpertId() {
        return expertId;
    }

    public void setExpertId(Long expertId) {
        this.expertId = expertId;
    }

    public String getExpertName() {
        return expertName;
    }

    public void setExpertName(String expertName) {
        this.expertName = expertName;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
