package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOMapper {
    private DAOMapper() {}

    public static User mapToUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getBoolean("is_blocked"),
                Role.valueOf(resultSet.getString("role").toUpperCase())
        );
    }
}
