package ru.rsreu.expertsandteams.config;

import ru.rsreu.expertsandteams.enums.RoleType;

import java.util.*;

import static ru.rsreu.expertsandteams.constant.Routes.*;

public class AuthConfig {

    private static final Map<RoleType, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        List<String> userUrlPatterns = new ArrayList<>();

        userUrlPatterns.add(PROFILE);
        userUrlPatterns.add(TEAMS);
        userUrlPatterns.add(LOGOUT);

        List<String> adminUrlPatterns = new ArrayList<>();

        adminUrlPatterns.add(PROFILE);
        adminUrlPatterns.add(ADMIN_DASHBOARD);
        adminUrlPatterns.add(LOGOUT);

        List<String> moderatorUrlPatterns = new ArrayList<>();

        moderatorUrlPatterns.add(PROFILE);
        moderatorUrlPatterns.add(MODERATOR_DASHBOARD);
        moderatorUrlPatterns.add(LOGOUT);

        List<String> expertUrlPatters = new ArrayList<>();

        expertUrlPatters.add(PROFILE);
        expertUrlPatters.add(LOGOUT);

        mapConfig.put(RoleType.USER, userUrlPatterns);
        mapConfig.put(RoleType.ADMIN, adminUrlPatterns);
        mapConfig.put(RoleType.MODERATOR, moderatorUrlPatterns);
        mapConfig.put(RoleType.EXPERT, expertUrlPatters);
    }

    public static Set<RoleType> getAllRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getRoleUrlPatterns(RoleType roleType) {
        return mapConfig.get(roleType);
    }
}