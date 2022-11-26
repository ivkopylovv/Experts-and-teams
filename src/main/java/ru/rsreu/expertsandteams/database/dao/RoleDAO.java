package ru.rsreu.expertsandteams.database.dao;

import com.prutzjow.resourcer.ProjectResourcer;
import com.prutzjow.resourcer.Resourcer;
import ru.rsreu.expertsandteams.data.Role;
import ru.rsreu.expertsandteams.database.ConnectionPool;

import javax.swing.text.html.Option;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDAO {
    private static volatile RoleDAO instance;
    private final Resourcer resourcer;

    private RoleDAO() {
        resourcer = ProjectResourcer.getInstance();
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

        public static RoleDAO getInstance() {
        synchronized (UserDAO.class) {
            if (instance == null) {
                instance = new RoleDAO();
            }
        }

        return instance;
    }
}
