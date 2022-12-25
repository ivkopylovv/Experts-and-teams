package ru.rsreu.expertsandteams.model.api.request;

import java.util.List;

public class SignUpRequest {
    private String name;
    private String username;
    private String password;
    private String role;
    private List<String> skills;

    public SignUpRequest(String name, String username, String password, String role, List<String> skills) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.skills = skills;
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

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }
}
