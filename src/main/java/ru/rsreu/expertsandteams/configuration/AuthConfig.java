package ru.rsreu.expertsandteams.configuration;

import ru.rsreu.expertsandteams.model.enums.Role;
import ru.rsreu.expertsandteams.model.enums.Route;

import java.util.*;

public class AuthConfig {
    private static final Map<Role, List<Route>> roleRoutes = Map.ofEntries(
            Map.entry(Role.USER, List.of(
                    Route.LOGOUT,
                    Route.TEAMS
            )),
            Map.entry(Role.EXPERT, List.of(
                    Route.LOGOUT
            )),
            Map.entry(Role.MODERATOR, List.of(
                    Route.LOGOUT
            )),
            Map.entry(Role.ADMIN, List.of(
                    Route.ADMIN_DASHBOARD
            ))
    );

    public static List<Route> getRoleRoutes(Role role) {
        return roleRoutes.get(role);
    }
}