package ru.rsreu.expertsandteams.support.mapper;

import ru.rsreu.expertsandteams.model.entity.*;
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

    public static TeamMessage mapToTeamMessage(ResultSet resultSet) throws SQLException {
        return new TeamMessage(
                resultSet.getLong("id"),
                new Team(
                        resultSet.getLong("team_id"),
                        resultSet.getString("team_name")
                ),
                new User(
                        resultSet.getString("user_name")
                ),
                resultSet.getString("message"),
                resultSet.getDate("message_date"),
                new User(
                        resultSet.getString("expert_name")
                )
        );
    }

    public static ExpertSkill mapToExpertSkill(ResultSet resultSet) throws SQLException {
        return new ExpertSkill(
                resultSet.getString("skill")
        );
    }

    public static LastMessageRequest mapToLastMessageRequest(ResultSet resultSet) throws SQLException {
        return new LastMessageRequest(
                new Team(
                        resultSet.getLong("team_id")
                ),
                new User(resultSet.getLong("user_id")),
                resultSet.getDate("request_date")
        );
    }
}
