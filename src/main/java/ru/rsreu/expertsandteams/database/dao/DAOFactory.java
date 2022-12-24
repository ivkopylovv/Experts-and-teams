package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.database.dao.impl.*;

public class DAOFactory {
    private DAOFactory() {
    }

    public static UserDAO getUserDAO() {
        return UserDAOImpl.getInstance();
    }

    public static SessionDAO getSessionDAO() {
        return SessionDAOImpl.getInstance();
    }

    public static ExpertSkillDAO getExpertSkillDAO() {
        return ExpertSkillDAOImpl.getInstance();
    }

    public static TeamDAO getTeamDAO() {
        return TeamDAOImpl.getInstance();
    }

    public static TeamJoinRequestDAO getTeamJoinRequestDAO() {
        return TeamJoinRequestDAOImpl.getInstance();
    }

    public static TeamMessageDAO getTeamMessageDAO() {
        return TeamMessageDAOImpl.getInstance();
    }

    public static LastMessageRequestDAO getLastMessageRequestDAO() {
        return LastMessageRequestDAOImpl.getInstance();
    }
}

