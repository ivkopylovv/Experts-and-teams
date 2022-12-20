package ru.rsreu.expertsandteams.database.dao.impl;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.model.entity.Session;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.database.dao.SessionDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SessionDAOImpl implements SessionDAO {
    private static volatile SessionDAOImpl instance;
    private final Resourcer resourcer;

    private SessionDAOImpl() {
        resourcer = ProjectResourcer.getInstance();
    }

//    public Optional<Session> findByUserId(Long userId) {
//        String query = resourcer.getString("session.query.find.by.user.id");
//
//        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
//            statement.setLong(1, userId);
//
//            ResultSet resultSet = statement.executeQuery();
//
//            while (resultSet.next()) {
//                Session session = new Session(
//                        resultSet.getLong("user_id"),
//                        resultSet.getTimestamp("expired_at")
//                );
//
//                return Optional.of(session);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return Optional.empty();
//    }

    public void save(Session session) {
        String query = resourcer.getString("session.query.save");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, session.getUser().getId());
            statement.setDate(2, new Date(session.getExpiredAt().getTime()));

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteByUserId(Long userId) {
        String query = resourcer.getString("session.query.delete");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, userId);

            statement.executeUpdate();
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
