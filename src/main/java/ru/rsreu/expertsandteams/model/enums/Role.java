package ru.rsreu.expertsandteams.model.enums;

public enum Role {
    ADMIN("Admin"),
    MODERATOR("Moderator"),
    EXPERT("Expert"),
    USER("User");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
