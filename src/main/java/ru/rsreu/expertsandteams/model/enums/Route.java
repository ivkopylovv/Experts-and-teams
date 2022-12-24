package ru.rsreu.expertsandteams.model.enums;

public enum Route {
    ADMIN_DASHBOARD_ADD_USER("Dashboard", "/admin-dashboard/add-user"),
    ADMIN_DASHBOARD_DELETE_USER("Dashboard", "/admin-dashboard/delete-user"),
    ADMIN_DASHBOARD_EDIT_USER("Dashboard", "/admin-dashboard/edit-user"),
    ADMIN_DASHBOARD("Dashboard", "/admin-dashboard"),

    MODERATOR_DASHBOARD("Dashboard", "/moderator-dashboard"),

    TEAMS_JOIN_REQUEST_DECISION("Join Request Decision", "/teams/join-request-decision"),
    TEAMS_JOIN_REQUEST("Available Teams", "/teams/join-request"),
    TEAMS_AVAILABLE("Available Teams", "/teams/available"),
    TEAM_LEAVE("Create Team", "/teams/leave"),
    TEAM_AVAILABLE_EXPERTS("Team Experts", "/teams/available-experts"),
    TEAM_EXPERTS("Team Experts", "/teams/experts"),
    TEAM_CREATE("Create Team", "/teams/create"),
    TEAMS_CHAT_LAST_MESSAGES("Team Chat Last Message", "/team/chat-last-messages"),
    TEAM_CHAT("Team Chat", "/teams/chat"),
    TEAMS("Teams", "/teams"),

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
