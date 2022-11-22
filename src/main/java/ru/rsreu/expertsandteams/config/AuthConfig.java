package ru.rsreu.expertsandteams.config;

import ru.rsreu.expertsandteams.enums.Role;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AuthConfig {

    private static final Map<Role, ArrayList<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        // TODO
    }

    public static Set<Role> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static ArrayList<String> getUrlPatternsForRole(Role role) {
        return mapConfig.get(role);
    }
}