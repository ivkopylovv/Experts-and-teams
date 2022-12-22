package ru.rsreu.expertsandteams.database.dao.impl;

import ru.rsreu.expertsandteams.database.dao.AbstractDAO;
import ru.rsreu.expertsandteams.model.entity.Session;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.database.dao.SessionDAO;
import ru.rsreu.expertsandteams.support.mapper.DAOMapper;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SessionDAOImpl extends AbstractDAO implements SessionDAO {
    private static volatile SessionDAOImpl instance;

    @Override
    public Optional<Session> findByUserId(Long userId) {
        String query = resourcer.getString("session.query.find.by.user.id");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, userId);

            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                return Optional.ofNullable(DAOMapper.mapToSession(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    @Override
    public List<Session> findAll() {
        String query = resourcer.getString("session.query.find.all.with.session");
        List<Session> sessions = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            ResultSet resultSet = st.executeQuery();

            while (resultSet.next()) {
                sessions.add(DAOMapper.mapToSession(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sessions;
    }

    public static SessionDAOImpl getInstance() {
        synchronized (SessionDAOImpl.class) {
            if (instance == null) {
                instance = new SessionDAOImpl();
            }
        }

        return instance;
    }
}
