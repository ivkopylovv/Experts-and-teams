package ru.rsreu.expertsandteams.constant;

import java.util.ArrayList;
import java.util.List;

public class Routes {
    public static final String SIGNIN = "signin";
    public static final String SIGNUP = "signup";

    public static final String LOGOUT = "logout";
    public static final String PROFILE = "profile";
    public static final String ADMIN_DASHBOARD = "admindashboard";
    public static final String MODERATOR_DASHBOARD = "moderatordashboard";

    private static final ArrayList<String> routes = new ArrayList<>();

    static {
        routes.add(SIGNIN);
        routes.add(SIGNUP);
        routes.add(LOGOUT);
        routes.add(PROFILE);
        routes.add(ADMIN_DASHBOARD);
        routes.add(MODERATOR_DASHBOARD);
    }

    public static List<String> getRoutes() {
        return routes;
    }
}
