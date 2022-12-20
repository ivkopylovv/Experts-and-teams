package ru.rsreu.expertsandteams.model.enums;

public enum AdminDashboardMode {
    ADD_USER("AddUser"),
    UPDATE_USER("UpdateUser"),
    DELETE_USERS("DeleteUsers");

    private final String mode;

    AdminDashboardMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
