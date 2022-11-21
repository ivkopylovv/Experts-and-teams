package ru.rsreu.expertsandteams.database.dao;

import com.prutzjow.resourcer.ProjectResourcer;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.data.role.Role;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.exception.UserNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private static final String FIND_BY_USERNAME = ProjectResourcer.getInstance().getString("user.query.find.by.username");
    private static final String FIND_ALL = ProjectResourcer.getInstance().getString("user.query.find.all");
    private static final String FIND_ALL_BY_BLOCK_STATUS =
            ProjectResourcer.getInstance().getString("user.query.find.all.by.block.status");
    private static final String FIND_ALL_AUTHORIZED = ProjectResourcer.getInstance().getString("user.query.find.all.authorized");
    private static final String FIND_ALL_BY_TEAM_ID = ProjectResourcer.getInstance().getString("user.query.find.all.by.teamid");

    public User findByUsername(String username) {
        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(FIND_BY_USERNAME)) {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<User> findAll() {
        ArrayList<User> users = new ArrayList<>();

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(FIND_ALL)) {
            ResultSet resultSet = statement.executeQuery();

            getUsersFromResultSet(users, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<User> findAllByBlockStatus(boolean isBlocked) {
        ArrayList<User> users = new ArrayList<>();

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(FIND_ALL_BY_BLOCK_STATUS)) {
            statement.setBoolean(1, isBlocked);

            ResultSet resultSet = statement.executeQuery();

            getUsersFromResultSet(users, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<User> findAllAuthorized() {
        ArrayList<User> users = new ArrayList<>();

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(FIND_ALL_AUTHORIZED)) {
            ResultSet resultSet = statement.executeQuery();

            getUsersFromResultSet(users, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<User> findAllByTeamId(long teamId) {
        ArrayList<User> users = new ArrayList<>();

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(FIND_ALL_BY_TEAM_ID)) {
            statement.setLong(1, teamId);

            ResultSet resultSet = statement.executeQuery();

            getUsersFromResultSet(users, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    private void getUsersFromResultSet(ArrayList<User> users, ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            User user = new User(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getBoolean(5)
            );

            users.add(user);
        }
    }
}
