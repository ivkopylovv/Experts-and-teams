package ru.rsreu.expertsandteams.database.dao;

public class DAOFactory {
    private static volatile DAOFactory instance;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        synchronized (DAOFactory.class) {
            if (instance == null) {
                instance = new DAOFactory();
            }
        }

        return instance;
    }

    public RoleDAO getRoleDAO() {
        return RoleDAO.getInstance();
    }

    public UserDAO getUserDAO() {
        return UserDAO.getInstance();
    }

    public SessionDAO getSessionDAO() {
        return SessionDAO.getInstance();
    }

    public ExpertSkillDAO getExpertSkillDAO() {
        return ExpertSkillDAO.getInstance();
    }
}

