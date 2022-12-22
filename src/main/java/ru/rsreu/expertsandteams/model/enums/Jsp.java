package ru.rsreu.expertsandteams.model.enums;

public enum Jsp {
    ADMIN_DASHBOARD("/jsp/pages/admin-dashboard.jsp"),
    MODERATOR_DASHBOARD("/jsp/pages/moderator-dashboard.jsp"),
    USER_TEAMS("/jsp/pages/user-teams/user-teams.jsp"),
    USER_TEAM_CHAT("/jsp/pages/user-team-chat/user-team-chat.jsp"),
    SIGNIN("/jsp/pages/signin.jsp"),
    SIGNUP("/jsp/pages/signup.jsp"),
    NOT_FOUND("/jsp/pages/404.jsp");

    private final String jspPath;

    Jsp(String jspPath) {
        this.jspPath = jspPath;
    }

    public String getJspPath() {
        return this.jspPath;
    }
}
