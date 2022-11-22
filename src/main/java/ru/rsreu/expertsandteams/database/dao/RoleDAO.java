package ru.rsreu.expertsandteams.database.dao;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.data.Role;
import ru.rsreu.expertsandteams.database.ConnectionPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO {
    private Resourcer resourcer;

    public RoleDAO() {
        resourcer = ProjectResourcer.getInstance();
    }

    public List<Role> findAll() {
        ArrayList<Role> roles = new ArrayList<>();
        String query = resourcer.getString("role.query.find.all");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Role role = new Role(
                        resultSet.getLong(1),
                        resultSet.getString(2)
                );

                roles.add(role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roles;
    }

    public Role findById(long id) {
        String query = resourcer.getString("role.query.find.by.id");

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(query)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new Role(id, resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Role> findByUserId(Long userId) {
        ArrayList<Role> roles = new ArrayList<>();
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
}
