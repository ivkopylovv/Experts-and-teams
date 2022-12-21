package ru.rsreu.expertsandteams.model.api.response;

public class UserResponse {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String role;
    private String[] skills;
    private Boolean isOnline;
    private Boolean isBlocked;

    public UserResponse(Long id, String name, String username, String password, String role, String[] skills, Boolean isOnline, Boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
        this.skills = skills;
        this.isOnline = isOnline;
        this.isBlocked = isBlocked;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public Boolean getOnline() {
        return isOnline;
    }

    public void setOnline(Boolean online) {
        isOnline = online;
    }

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }
}
