package ru.rsreu.expertsandteams.database.dao;

import com.prutzjow.resourcer.ProjectResourcer;
import ru.rsreu.expertsandteams.data.role.Role;
import ru.rsreu.expertsandteams.database.ConnectionPool;
import ru.rsreu.expertsandteams.exception.RoleNotFoundException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoleDAO {

    private static final String FIND_ALL = ProjectResourcer.getInstance().getString("role.query.find.all");
    private static final String GET_BY_ID = ProjectResourcer.getInstance().getString("role.query.find.by.id");

    public ArrayList<Role> findAll() {
        ArrayList<Role> roles = new ArrayList<>();

        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(FIND_ALL)) {
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

    public Role getById(long id) {
        try (PreparedStatement statement = ConnectionPool.getConnection().prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                return new Role(id, resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new RoleNotFoundException();
    }

    // TODO: think about it
    public Role getById(String id) {
        return getById(Integer.parseInt(id));
    }
}
