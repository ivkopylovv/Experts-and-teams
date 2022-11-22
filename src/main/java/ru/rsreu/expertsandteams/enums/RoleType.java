package ru.rsreu.expertsandteams.enums;

public enum RoleType {
    ADMIN("Admin"),
    MODERATOR("Moderator"),
    EXPERT("Expert"),
    USER("User");

    private final String role;

    RoleType(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
