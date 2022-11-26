package ru.rsreu.expertsandteams.database.dao;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.data.Session;
import ru.rsreu.expertsandteams.database.ConnectionPool;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class SessionDAO {
    private static volatile SessionDAO instance;
    private final Resourcer resourcer;

    private SessionDAO() {
        resourcer = ProjectResourcer.getInstance();
    }

    public Optional<Session> findByUserId(Long userId) {
        String query = resourcer.getString("session.query.find.by.user.id");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Session session = new Session(
                        resultSet.getLong("user_id"),
                        resultSet.getTimestamp("expired_at")
                );

                return Optional.of(session);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void save(Session session) {
        String query = resourcer.getString("session.query.save");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, session.getUserId());
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

    public static SessionDAO getInstance() {
        synchronized (UserDAO.class) {
            if (instance == null) {
                instance = new SessionDAO();
            }
        }

        return instance;
    }
}
