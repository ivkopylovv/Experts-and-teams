package ru.rsreu.expertsandteams.model.api.response;

import java.util.List;

public class AvailableExpertResponse {
    private Long expertId;
    private String expertName;
    private List<String> skills;

    public AvailableExpertResponse(Long expertId, String expertName, List<String> skills) {
        this.expertId = expertId;
        this.expertName = expertName;
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

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
