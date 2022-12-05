package ru.rsreu.expertsandteams.database.dao.impl;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.data.Role;
import ru.rsreu.expertsandteams.data.User;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.database.dao.UserDAO;
import ru.rsreu.expertsandteams.enums.RoleType;
import ru.rsreu.expertsandteams.helper.UserHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl implements UserDAO {
    private static volatile UserDAOImpl instance;
    private final Resourcer resourcer;

    private UserDAOImpl() {
        resourcer = ProjectResourcer.getInstance();
    }

    public Optional<User> findById(Long id) {
        String query = this.resourcer.getString("user.query.find.by.id");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("is_blocked")
                );

                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<User> findByUsername(String username) {
        String query = this.resourcer.getString("user.query.find.by.username");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getBoolean(5)
                );

                return Optional.of(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<User> findAll() {
        ArrayList<User> users = new ArrayList<>();
        String query = this.resourcer.getString("user.query.find.all");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = UserHelper.getFromResultSet(resultSet);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void addRoleToUser(User user, Role role) {
        String query = this.resourcer.getString("user.query.add.role");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, user.getId());
            statement.setLong(2, role.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAllByBlockStatus(boolean isBlocked) {
        ArrayList<User> users = new ArrayList<>();
        String query = this.resourcer.getString("user.query.find.all.by.block.status");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setBoolean(1, isBlocked);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = UserHelper.getFromResultSet(resultSet);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<User> findAllAuthorized() {
        ArrayList<User> users = new ArrayList<>();
        String query = this.resourcer.getString("user.query.find.all.authorized");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = UserHelper.getFromResultSet(resultSet);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public List<User> findAllByTeamId(long teamId) {
        ArrayList<User> users = new ArrayList<>();
        String query = this.resourcer.getString("user.query.find.all.by.teamid");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, teamId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = UserHelper.getFromResultSet(resultSet);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void delete(List<Long> userIds) {
        String query = resourcer.getString("user.query.delete");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            for (Long userId : userIds) {
                statement.setLong(1, userId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<User> save(User user) {
        String query = resourcer.getString("user.query.save.user");
        String[] returnId = {"id"};

        try (PreparedStatement statement = ConnectionPool
                .getConnection()
                .prepareStatement(query, returnId)
        ) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);

                    user.setId(id);

                    return Optional.of(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<User> update(User user) {
        String query = resourcer.getString("user.query.update");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.getBlocked());
            statement.setLong(5, user.getId());

            statement.executeUpdate();

            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<User> findAllWithSession() {
        String query = resourcer.getString("user.query.find.all.with.session");
        List<User> users = new ArrayList<>();

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getBoolean("is_blocked"),
                        resultSet.getTimestamp("expired_at")
                );

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (User user: users) {
            String roleQuery = resourcer.getString("role.query.find.by.user.id");
            List<RoleType> roles = new ArrayList<>();

            try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(roleQuery)) {
                statement.setLong(1, user.getId());
                ResultSet resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    RoleType roleType = RoleType.valueOf(resultSet.getString("name").toUpperCase());
                    roles.add(roleType);
                }

                user.setRoles(roles);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return users;
    }

    public static UserDAOImpl getInstance() {
        synchronized (UserDAOImpl.class) {
            if (instance == null) {
                instance = new UserDAOImpl();
            }
        }

        return instance;
    }
}
