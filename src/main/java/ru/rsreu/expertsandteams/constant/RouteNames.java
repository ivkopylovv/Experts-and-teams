package ru.rsreu.expertsandteams.constant;

import java.util.HashMap;

import static ru.rsreu.expertsandteams.constant.Routes.*;

public class RouteNames {
    private static final String SIGNIN_NAME = "Sign In";
    private static final String SIGNUP_NAME = "Sign Up";
    private static final String LOGOUT_NAME = "Logout";
    private static final String PROFILE_NAME = "Profile";
    private static final String ADMIN_DASHBOARD_NAME = "Dashboard";
    private static final String UNKNOWN = "Unknown";

    private static final HashMap<String, String> routeNames = new HashMap<>();

    static {
        routeNames.put(SIGNIN, SIGNIN_NAME);
        routeNames.put(SIGNUP, SIGNUP_NAME);
        routeNames.put(LOGOUT, LOGOUT_NAME);
        routeNames.put(PROFILE, PROFILE_NAME);
        routeNames.put(ADMIN_DASHBOARD, ADMIN_DASHBOARD_NAME);
    }

    public static String getRouteName(String route) {
        if (Routes.getRoutes().contains(route)) {
            return routeNames.get(route);
        }

        return UNKNOWN;
    }
}
