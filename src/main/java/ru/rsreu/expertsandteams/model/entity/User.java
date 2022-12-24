package ru.rsreu.expertsandteams.model.entity;

import java.security.Principal;
import java.util.Date;

public class User implements Principal {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Boolean isBlocked;
    private String role;
    private String[] skills;

    public User(Long id) {
        this.id = id;
    }

    public User(String name) {
        this.name = name;
    }

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public User(Long id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(Long id, String name, String username, String password, Boolean isBlocked, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
        this.role = role;
    }

    public User(String name, String username, String password, Boolean isBlocked, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isOnline(Date expiredAt) {
        return expiredAt != null && expiredAt.after(new Date(System.currentTimeMillis()));
    }

    public String[] getSkills() {
        return skills;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    @Override
    public String getName() {
        return name;
    }
}
