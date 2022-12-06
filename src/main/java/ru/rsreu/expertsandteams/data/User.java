package ru.rsreu.expertsandteams.data;

import ru.rsreu.expertsandteams.enums.RoleType;

import java.io.Serializable;
import java.security.Principal;
import java.util.Date;
import java.util.List;

public class User implements Principal {
    private Long id;
    private String name;
    private String username;
    private String password;
    private Boolean isBlocked;
    private Date sessionExpiredAt;

    private List<RoleType> roles;
    private List<Skill> skills;

    public User(Long id, String name, String username, String password, Boolean isBlocked, List<RoleType> roles) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
        this.roles = roles;
    }

    public User(Long id, String name, String username, String password, Boolean isBlocked, Date sessionExpiredAt) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
        this.sessionExpiredAt = sessionExpiredAt;
    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.isBlocked = false;
    }

    public User(Long id, String name, String username, String password, Boolean isBlocked) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.isBlocked = isBlocked;
    }

    public User(String name, String username, String password, List<Skill> skills) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.skills = skills;
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

    public Boolean getBlocked() {
        return isBlocked;
    }

    public void setBlocked(Boolean blocked) {
        isBlocked = blocked;
    }

    public List<RoleType> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleType> roles) {
        this.roles = roles;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public boolean isOnline() {
        Date currentDate = new Date();
        Date sessionExpiredDate = getSessionExpiredAt();

        return sessionExpiredDate != null && sessionExpiredDate.after(currentDate);
    }

    public Date getSessionExpiredAt() {
        return sessionExpiredAt;
    }

    public void setSessionExpiredAt(Date sessionExpiredAt) {
        this.sessionExpiredAt = sessionExpiredAt;
    }
}
