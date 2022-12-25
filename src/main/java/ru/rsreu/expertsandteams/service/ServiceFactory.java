package ru.rsreu.expertsandteams.service;

import ru.rsreu.expertsandteams.service.impl.*;

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

    public static TeamMessageService getTeamMessageService() {
        return TeamMessageServiceImpl.getInstance();
    }

    public static NotificationService getNotificationService() {
        return NotificationServiceImpl.getInstance();
    }
}
