package ru.rsreu.expertsandteams.configuration;

import ru.rsreu.expertsandteams.api.command.*;
import ru.rsreu.expertsandteams.api.command.AvailableTeamsCommand;
import ru.rsreu.expertsandteams.model.enums.Route;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CommandConfig {
    private static final Map<Route, FrontCommand> commands = Map.ofEntries(
            Map.entry(Route.ADMIN_DASHBOARD_ADD_USER, new AdminDashboardAddUserCommand()),
            Map.entry(Route.ADMIN_DASHBOARD_DELETE_USER, new AdminDashboardDeleteUserCommand()),
            Map.entry(Route.ADMIN_DASHBOARD_EDIT_USER, new AdminDashboardEditUserCommand()),
            Map.entry(Route.ADMIN_DASHBOARD, new AdminDashboardCommand()),

            Map.entry(Route.MODERATOR_DASHBOARD, new ModeratorDashboardCommand()),

            Map.entry(Route.NOTIFICATIONS, new NotificationCommand()),

            Map.entry(Route.TEAMS_JOIN_REQUEST_DECISION, new MakeRequestDecisionCommand()),
            Map.entry(Route.TEAMS_JOIN_REQUEST, new JoinRequestCommand()),
            Map.entry(Route.TEAMS_AVAILABLE, new AvailableTeamsCommand()),
            Map.entry(Route.TEAM_AVAILABLE_EXPERTS, new TeamAvailableExpertsCommand()),
            Map.entry(Route.TEAM_EXPERTS, new TeamExpertsCommand()),
            Map.entry(Route.TEAM_LEAVE, new LeaveTeamCommand()),
            Map.entry(Route.TEAM_CREATE, new CreateTeamCommand()),
            Map.entry(Route.TEAMS_CHAT_LAST_MESSAGES, new TeamActualChatHistoryPartCommand()),
            Map.entry(Route.TEAM_CHAT, new TeamChatHistoryCommand()),
            Map.entry(Route.TEAMS, new TeamsCommand()),

            Map.entry(Route.SIGNIN, new SigninCommand()),
            Map.entry(Route.SIGNUP, new SignupCommand()),
            Map.entry(Route.LOGOUT, new LogoutCommand()),

            Map.entry(Route.NOT_FOUND, new NotFoundCommand())
    );

    private static final List<Route> commandRoutes = Arrays.asList(
            Route.ADMIN_DASHBOARD_ADD_USER,
            Route.ADMIN_DASHBOARD_DELETE_USER,
            Route.ADMIN_DASHBOARD_EDIT_USER,
            Route.ADMIN_DASHBOARD,

            Route.MODERATOR_DASHBOARD,

            Route.NOTIFICATIONS,

            Route.TEAMS_JOIN_REQUEST_DECISION,
            Route.TEAMS_JOIN_REQUEST,
            Route.TEAMS_AVAILABLE,
            Route.TEAM_LEAVE,
            Route.TEAM_AVAILABLE_EXPERTS,
            Route.TEAM_EXPERTS,
            Route.TEAM_CREATE,
            Route.TEAMS_CHAT_LAST_MESSAGES,
            Route.TEAM_CHAT,
            Route.TEAMS,

            Route.SIGNIN,
            Route.SIGNUP,
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
