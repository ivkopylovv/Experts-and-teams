package ru.rsreu.expertsandteams.configuration;

import ru.rsreu.expertsandteams.api.command.*;
import ru.rsreu.expertsandteams.model.enums.Route;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CommandConfig {
    private static final Map<Route, FrontCommand> commands = Map.ofEntries(
            Map.entry(Route.ADMIN_DASHBOARD, new AdminDashboardCommand()),
            Map.entry(Route.MODERATOR_DASHBOARD, new ModeratorDashboardCommand()),
            Map.entry(Route.USER_TEAMS, new UserTeamsCommand()),
            Map.entry(Route.USER_TEAM_CHAT, new UserTeamChatCommand()),

            Map.entry(Route.SIGNIN, new SigninCommand()),
            Map.entry(Route.LOGOUT, new LogoutCommand()),

            Map.entry(Route.NOT_FOUND, new NotFoundCommand())
    );

    private static final List<Route> commandRoutes = Arrays.asList(
            Route.ADMIN_DASHBOARD,
            Route.MODERATOR_DASHBOARD,
            Route.USER_TEAMS,
            Route.USER_TEAM_CHAT,

            Route.SIGNIN,
            Route.LOGOUT,

            Route.NOT_FOUND
    );

    public static FrontCommand getCommand(String path) {
        for (Route route : commandRoutes) {
            if (route.getRelative().equalsIgnoreCase(path)) {
                return commands.get(route);
            }
        }

        return new NotFoundCommand();
    }
}
