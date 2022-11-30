package ru.rsreu.expertsandteams.database.dao;

import ru.rsreu.expertsandteams.database.dao.impl.ExpertSkillDAOImpl;
import ru.rsreu.expertsandteams.database.dao.impl.RoleDAOImpl;
import ru.rsreu.expertsandteams.database.dao.impl.SessionDAOImpl;
import ru.rsreu.expertsandteams.database.dao.impl.UserDAOImpl;

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
}

