package ru.rsreu.expertsandteams.support.helper;

import ru.rsreu.expertsandteams.configuration.AuthConfig;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Role;
import ru.rsreu.expertsandteams.model.enums.Route;

import java.util.List;

public class PermissionHelper {
    private PermissionHelper() {
    }

    public static boolean hasPermission(String path, User user) {
        List<Route> routes = AuthConfig.getRoleRoutes(
                Role.valueOf(user.getRole().toUpperCase())
        );

        for (Route route : routes) {
            if (path.equalsIgnoreCase(route.getRelative())) {
                return true;
            }
        }

        return false;
    }
}
