package ru.rsreu.expertsandteams.service;

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
}
