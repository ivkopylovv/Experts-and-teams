package ru.rsreu.expertsandteams.model.entity;

public class ExpertSkill {
    private User user;
    private String skill;

    public ExpertSkill(User user, String skill) {
        this.user = user;
        this.skill = skill;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
