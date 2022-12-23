package ru.rsreu.expertsandteams.database.dao.impl;

import ru.rsreu.expertsandteams.database.dao.AbstractDAO;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.database.dao.UserDAO;
import ru.rsreu.expertsandteams.support.mapper.DAOMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDAOImpl extends AbstractDAO implements UserDAO {
    private static volatile UserDAOImpl instance;

    public Optional<User> findById(Long id) {
        String query = resourcer.getString("user.query.find.by.id");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User user = DAOMapper.mapToUser(rs);

                return Optional.ofNullable(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public Optional<User> findByUsername(String username) {
        String query = resourcer.getString("user.query.find.by.username");

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User user = DAOMapper.mapToUser(rs);

                return Optional.ofNullable(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public List<User> findAll(Long id) {
        List<User> users = new ArrayList<>();
        String query = resourcer.getString("user.query.find.all.without.current.user");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user = DAOMapper.mapToUser(rs);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public void delete(User user) {
        String query = resourcer.getString("user.query.delete");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, user.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        String query = resourcer.getString("user.query.delete");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeBlockStatus(User user) {
        String query = resourcer.getString("user.query.update.blocked");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setBoolean(1, !user.getBlocked());
            st.setLong(2, user.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<User> save(User user) {
        String query = resourcer.getString("user.query.save.user");
        String[] returnId = {"id"};

        try (PreparedStatement st = connection.prepareStatement(query, returnId)) {
            st.setString(1, user.getName());
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());
            st.setString(4, user.getRole());

            int affectedRows = st.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed");
            }

            try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long id = generatedKeys.getLong(1);

                    user.setId(id);

                    return Optional.ofNullable(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void update(User user) {
        String query = resourcer.getString("user.query.update");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setString(1, user.getName());
            st.setString(2, user.getUsername());
            st.setString(3, user.getPassword());
            st.setLong(4, user.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> findAllByTeamId(long teamId) {
        List<User> users = new ArrayList<>();
        String query = this.resourcer.getString("user.query.find.all.by.teamid");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, teamId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = DAOMapper.mapToUser(resultSet);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public List<User> findAllWithoutAdmins(Long id) {
        List<User> users = new ArrayList<>();
        String query = resourcer.getString("user.query.find.all.without.admin");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, id);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                User user = DAOMapper.mapToUser(rs);

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
