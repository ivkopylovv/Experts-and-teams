package ru.rsreu.expertsandteams.database.dao.impl;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.data.Role;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.database.dao.RoleDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDAOImpl implements RoleDAO {
    private static volatile RoleDAOImpl instance;
    private final Resourcer resourcer;

    private RoleDAOImpl() {
        resourcer = ProjectResourcer.getInstance();
    }

    public List<Role> findByUserId(Long userId) {
        List<Role> roles = new ArrayList<>();
        String query = resourcer.getString("role.query.find.by.user.id");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, userId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Role role = new Role(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );

                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    public Optional<Role> findByName(String roleName) {
        String query = resourcer.getString("role.query.find.by.name");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setString(1, roleName);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Role role = new Role(
                        resultSet.getLong("id"),
                        resultSet.getString("name")
                );
                return Optional.of(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public static RoleDAOImpl getInstance() {
        synchronized (UserDAOImpl.class) {
            if (instance == null) {
                instance = new RoleDAOImpl();
            }
        }

        return instance;
    }
}
