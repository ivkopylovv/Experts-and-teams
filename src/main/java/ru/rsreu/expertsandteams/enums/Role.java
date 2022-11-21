package ru.rsreu.expertsandteams.enums;

public enum Role {
    ADMIN("Admin"),
    MODERATOR("Moderator"),
    EXPERT("Expert"),
    USER("User");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
