package ru.rsreu.expertsandteams.model.entity;

import ru.rsreu.expertsandteams.model.enums.Role;

import java.security.Principal;

public class User implements Principal {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Boolean isBlocked;
    private Role role;

    public User(Long id, String name, String username, String password, Boolean isBlocked, Role role) {
        this.id = id;
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

    @Override
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

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
