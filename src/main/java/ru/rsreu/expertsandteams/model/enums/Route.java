package ru.rsreu.expertsandteams.model.enums;

public enum Route {
    ADMIN_DASHBOARD("Dashboard", "/admin-dashboard"),
    MODERATOR_DASHBOARD("Dashboard", "/moderator-dashboard"),
    USER_TEAMS("User Teams", "/user-teams"),
    USER_TEAM_CHAT("User Teams", "/user-teams/chat"),
    LOGOUT("Logout", "/logout"),
    SIGNIN("Sign In", "/signin"),
    SIGNUP("Sign Up", "/signup"),
    TEAMS("Teams", "/teams"),
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
