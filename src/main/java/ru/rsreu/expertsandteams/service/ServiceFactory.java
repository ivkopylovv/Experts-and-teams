package ru.rsreu.expertsandteams.service;

import ru.rsreu.expertsandteams.service.impl.SessionServiceImpl;
import ru.rsreu.expertsandteams.service.impl.TeamJoinRequestServiceImpl;
import ru.rsreu.expertsandteams.service.impl.TeamServiceImpl;
import ru.rsreu.expertsandteams.service.impl.UserServiceImpl;

public class ServiceFactory {
    private ServiceFactory() {
    }

    public static UserService getUserService() {
        return UserServiceImpl.getInstance();
    }

    public static TeamService getTeamService() {
        return TeamServiceImpl.getInstance();
    }

    public static SessionService getSessionService() {
        return SessionServiceImpl.getInstance();
    }

    public static TeamJoinRequestService getTeamJoinRequestService() {
        return TeamJoinRequestServiceImpl.getInstance();
    }
}
