package ru.rsreu.expertsandteams.configuration;

import ru.rsreu.expertsandteams.model.enums.Role;
import ru.rsreu.expertsandteams.model.enums.Route;

import java.util.*;

public class AuthConfig {
    private static final Map<Role, List<Route>> roleRoutes = Map.ofEntries(
            Map.entry(Role.USER, List.of(
                    Route.LOGOUT,
                    Route.TEAMS_JOIN_REQUEST_DECISION,
                    Route.TEAMS_JOIN_REQUEST,
                    Route.TEAMS_AVAILABLE,
                    Route.TEAM_LEAVE,
                    Route.TEAM_CREATE,
                    Route.TEAMS_CHAT_LAST_MESSAGES,
                    Route.TEAM_CHAT,
                    Route.TEAMS
            )),
            Map.entry(Role.EXPERT, List.of(
                    Route.LOGOUT,
                    Route.TEAM_LEAVE,
                    Route.TEAM_CHAT,
                    Route.TEAMS
            )),
            Map.entry(Role.MODERATOR, List.of(
                    Route.LOGOUT,
                    Route.MODERATOR_DASHBOARD
            )),
            Map.entry(Role.ADMIN, List.of(
                    Route.LOGOUT,
                    Route.ADMIN_DASHBOARD_ADD_USER,
                    Route.ADMIN_DASHBOARD_DELETE_USER,
                    Route.ADMIN_DASHBOARD_EDIT_USER,
                    Route.ADMIN_DASHBOARD
            ))
    );

    private static final Map<Role, Route> roleStartPage = Map.ofEntries(
            Map.entry(Role.USER, Route.TEAMS),
            Map.entry(Role.MODERATOR, Route.MODERATOR_DASHBOARD),
            Map.entry(Role.ADMIN, Route.ADMIN_DASHBOARD),
            Map.entry(Role.EXPERT, Route.TEAMS)
    );

    public static Route getStartPage(Role role) {
        return roleStartPage.get(role);
    }

    public static List<Route> getRoleRoutes(Role role) {
        return roleRoutes.get(role);
    }
}