package ru.rsreu.expertsandteams.model.api.request;

public class SignUpRequest {
    private String name;
    private String username;
    private String password;
    private String role;
    private String[] skill;

    public SignUpRequest(String name, String username, String password, String role, String[] skill) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.skill = skill;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String[] getSkill() {
        return skill;
    }

    public void setSkill(String[] skill) {
        this.skill = skill;
    }
}
