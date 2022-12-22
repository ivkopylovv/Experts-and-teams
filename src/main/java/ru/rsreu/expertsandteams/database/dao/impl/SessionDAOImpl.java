package ru.rsreu.expertsandteams.database.dao.impl;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.model.entity.Session;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.database.dao.SessionDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

public class SessionDAOImpl implements SessionDAO {
    private static volatile SessionDAOImpl instance;
    private final Resourcer resourcer;

    private SessionDAOImpl() {
        resourcer = ProjectResourcer.getInstance();
    }

    // TODO: realisation
    public Optional<Session> findByUserId(Long userId) {
        return Optional.empty();
    }

    public void save(Session session) {
        String query = resourcer.getString("session.query.save");

        try (PreparedStatement st = ConnectionPool.getConnection().prepareStatement(query)) {
            st.setLong(1, session.getUser().getId());
            st.setDate(2, new Date(session.getExpiredAt().getTime()));

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByUserId(Long userId) {
        String query = resourcer.getString("session.query.delete");

        try (PreparedStatement st = ConnectionPool.getConnection().prepareStatement(query)) {
            st.setLong(1, userId);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static SessionDAOImpl getInstance() {
        synchronized (UserDAOImpl.class) {
            if (instance == null) {
                instance = new SessionDAOImpl();
            }
        }

        return instance;
    }
}
