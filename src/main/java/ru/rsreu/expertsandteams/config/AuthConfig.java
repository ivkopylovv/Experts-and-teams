package ru.rsreu.expertsandteams.config;

import ru.rsreu.expertsandteams.enums.RoleType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static ru.rsreu.expertsandteams.constant.Routes.ADMIN_DASHBOARD;
import static ru.rsreu.expertsandteams.constant.Routes.PROFILE;

public class AuthConfig {

    private static final Map<RoleType, ArrayList<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        ArrayList<String> userUrlPatterns = new ArrayList<>();

        userUrlPatterns.add(PROFILE);

        ArrayList<String> adminUrlPatterns = new ArrayList<>();

        adminUrlPatterns.add(PROFILE);
        adminUrlPatterns.add(ADMIN_DASHBOARD);

        ArrayList<String> moderatorUrlPatterns = new ArrayList<>();

        moderatorUrlPatterns.add(PROFILE);

        ArrayList<String> expertUrlPatters = new ArrayList<>();

        expertUrlPatters.add(PROFILE);

        mapConfig.put(RoleType.USER, userUrlPatterns);
        mapConfig.put(RoleType.ADMIN, adminUrlPatterns);
        mapConfig.put(RoleType.MODERATOR, moderatorUrlPatterns);
        mapConfig.put(RoleType.EXPERT, expertUrlPatters);
    }

    public static Set<RoleType> getAllRoles() {
        return mapConfig.keySet();
    }

    public static ArrayList<String> getRoleUrlPatterns(RoleType roleType) {
        return mapConfig.get(roleType);
    }
}