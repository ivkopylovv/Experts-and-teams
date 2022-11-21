package ru.rsreu.expertsandteams.database.dao;

public class DAOFactory {
    private static volatile DAOFactory instance;

    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        if (instance == null) {
            synchronized (DAOFactory.class) {
                instance = new DAOFactory();
            }
        }

        return instance;
    }

    public RoleDAO getRoleDAO() {
        return new RoleDAO();
    }

    public UserDAO getUserDAO() {
        return new UserDAO();
    }

    public SessionDAO getSessionDAO() {
        return new SessionDAO();
    }
}
