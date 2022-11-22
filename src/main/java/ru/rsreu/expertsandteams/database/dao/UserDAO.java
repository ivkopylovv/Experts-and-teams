package ru.rsreu.expertsandteams.database.dao;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {
    private Resourcer resourcer;

    public UserDAO() {
        resourcer = ProjectResourcer.getInstance();
    }

    public User findById(Long id) {
        String query = this.resourcer.getString("user.query.find.by.id");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("is_blocked")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public User findByUsername(String username) {
        String query = this.resourcer.getString("user.query.find.by.username");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
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
        String query = this.resourcer.getString("user.query.find.all");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            getUsersFromResultSet(users, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<User> findAllByBlockStatus(boolean isBlocked) {
        ArrayList<User> users = new ArrayList<>();
        String query = this.resourcer.getString("user.query.find.all.by.block.status");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
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
        String query = this.resourcer.getString("user.query.find.all.authorized");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            getUsersFromResultSet(users, resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public ArrayList<User> findAllByTeamId(long teamId) {
        ArrayList<User> users = new ArrayList<>();
        String query = this.resourcer.getString("user.query.find.all.by.teamid");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
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
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    resultSet.getBoolean("is_blocked")
            );

            users.add(user);
        }
    }
}
