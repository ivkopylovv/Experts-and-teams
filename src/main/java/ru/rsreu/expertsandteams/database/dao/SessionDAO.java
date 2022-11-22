package ru.rsreu.expertsandteams.database.dao;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.data.Session;
import ru.rsreu.expertsandteams.database.ConnectionPool;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SessionDAO {
    private final Resourcer resourcer;

    public SessionDAO() {
        resourcer = ProjectResourcer.getInstance();
    }

    public Session findByUserId(Long userId) {
        String query = resourcer.getString("session.query.find.by.user.id");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new Session(
                        resultSet.getLong("user_id"),
                        resultSet.getTimestamp("expired_at")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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
}
