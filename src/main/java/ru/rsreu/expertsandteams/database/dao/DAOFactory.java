package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.database.dao.impl.*;

public class DAOFactory {
    private DAOFactory() {
    }

    public static RoleDAO getRoleDAO() {
        return RoleDAOImpl.getInstance();
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
}

