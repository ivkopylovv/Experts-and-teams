package ru.rsreu.expertsandteams.helper;

import ru.rsreu.expertsandteams.config.AuthConfig;
import ru.rsreu.expertsandteams.enums.RoleType;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PermissionHelper {
    public static boolean isSecurityPage(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        Set<RoleType> allRoles = AuthConfig.getAllRoles();

        for (RoleType role : allRoles) {
            ArrayList<String> roleUrlPatterns = AuthConfig.getRoleUrlPatterns(role);

            if (roleUrlPatterns != null && roleUrlPatterns.contains(pathInfo)) {
                return true;
            }
        }

        return false;
    }

    public static boolean hasPermission(HttpServletRequest request, List<RoleType> userRoles) {
        String pathInfo = request.getPathInfo();
        Set<RoleType> allRoles = AuthConfig.getAllRoles();

        for (RoleType role : allRoles) {
            if (!userRoles.contains(role)) {
                continue;
            }

            ArrayList<String> roleUrlPatterns = AuthConfig.getRoleUrlPatterns(role);

            if (roleUrlPatterns != null && roleUrlPatterns.contains(pathInfo)) {
                return true;
            }
        }

        return false;
    }
}
