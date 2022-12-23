package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.model.entity.Session;
import ru.rsreu.expertsandteams.model.entity.Team;
import ru.rsreu.expertsandteams.model.entity.TeamJoinRequest;
import ru.rsreu.expertsandteams.model.entity.User;
import ru.rsreu.expertsandteams.model.enums.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOMapper {
    private DAOMapper() {}

    public static TeamJoinRequest mapToFullTeamJoinRequest(ResultSet resultSet) throws SQLException {
        return new TeamJoinRequest(
                resultSet.getLong("id"),
                new User(resultSet.getLong("user_id")),
                new Team(resultSet.getLong("team_id")),
                resultSet.getString("message")
        );
    }

    public static TeamJoinRequest mapToTeamJoinRequest(ResultSet resultSet) throws SQLException {
        return new TeamJoinRequest(
                resultSet.getLong("id"),
                new User(resultSet.getString("name")),
                resultSet.getString("message")
        );
    }

    public static User mapToUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getBoolean("is_blocked"),
                resultSet.getString("role")
        );
    }

    public static User mapToSessionUser(ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("user_id"),
                resultSet.getString("name"),
                resultSet.getString("username"),
                resultSet.getString("password"),
                resultSet.getBoolean("is_blocked"),
                resultSet.getString("role")
        );
    }

    public static Session mapToSession(ResultSet resultSet) throws SQLException {
        return new Session(
                resultSet.getTimestamp("expired_at"),
                mapToSessionUser(resultSet)
        );
    }

    public static Team mapToTeam(ResultSet resultSet) throws SQLException {
        return new Team(
                resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getLong("members_count"),
                new User(resultSet.getLong("captain_id"))
        );
    }
}
