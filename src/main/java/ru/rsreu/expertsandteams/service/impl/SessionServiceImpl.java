package ru.rsreu.expertsandteams.service.impl;

import ru.rsreu.expertsandteams.database.dao.DAOFactory;
import ru.rsreu.expertsandteams.database.dao.SessionDAO;
import ru.rsreu.expertsandteams.model.entity.Session;
import ru.rsreu.expertsandteams.service.SessionService;

import java.util.Optional;

public class SessionServiceImpl implements SessionService {
    private static volatile SessionServiceImpl instance;

    private final SessionDAO sessionDAO;

    public SessionServiceImpl(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    @Override
    public Optional<Session> getSession(Long userId) {
        return sessionDAO.findByUserId(userId);
    }

    public static SessionServiceImpl getInstance() {
        synchronized (SessionServiceImpl.class) {
            if (instance == null) {
                instance = new SessionServiceImpl(
                        DAOFactory.getSessionDAO()
                );
            }
        }

        return instance;
    }
}
