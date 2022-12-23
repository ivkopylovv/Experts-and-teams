package ru.rsreu.expertsandteams.model.api.response;

import ru.rsreu.expertsandteams.model.enums.Role;

public class RoleResponse {
    private Role role;
    private Long userId;

    public RoleResponse(Role role, Long userId) {
        this.role = role;
        this.userId = userId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
