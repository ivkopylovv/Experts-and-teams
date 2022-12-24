package ru.rsreu.expertsandteams.model.enums;

public enum Route {
    ADMIN_DASHBOARD_ADD_USER("Dashboard", "/admin-dashboard/add-user"),
    ADMIN_DASHBOARD_DELETE_USER("Dashboard", "/admin-dashboard/delete-user"),
    ADMIN_DASHBOARD_EDIT_USER("Dashboard", "/admin-dashboard/edit-user"),
    ADMIN_DASHBOARD("Dashboard", "/admin-dashboard"),

    MODERATOR_DASHBOARD("Dashboard", "/moderator-dashboard"),

    TEAMS_JOIN_REQUEST("Available Teams", "/teams/join-request"),
    TEAMS_AVAILABLE("Available Teams", "/teams/available"),
    TEAM_LEAVE("Create Team", "/teams/leave"),
    TEAM_CREATE("Create Team", "/teams/create"),
    TEAMS("Teams", "/teams"),
    TEAM_CHAT("Team Chat", "/teams/chat"),

    LOGOUT("Logout", "/logout"),
    SIGNIN("Sign In", "/signin"),
    SIGNUP("Sign Up", "/signup"),

    NOT_FOUND("404", "/404");

    private final String name;
    private final String path;

    Route(String name, String path) {
        this.name = name;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getRelative() {
        return path;
    }

    public String getAbsolute() {
        return path.substring(1);
    }
}
