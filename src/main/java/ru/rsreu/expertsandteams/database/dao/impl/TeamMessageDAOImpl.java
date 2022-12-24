package ru.rsreu.expertsandteams.database.dao.impl;

import ru.rsreu.expertsandteams.database.dao.AbstractDAO;
import ru.rsreu.expertsandteams.database.dao.TeamMessageDAO;
import ru.rsreu.expertsandteams.model.entity.TeamMessage;
import ru.rsreu.expertsandteams.support.mapper.DAOMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.*;

public class TeamMessageDAOImpl extends AbstractDAO implements TeamMessageDAO {
    private static volatile TeamMessageDAOImpl instance;

    @Override
    public List<TeamMessage> findByTeamId(Long teamId) {
        String query = resourcer.getString("team.message.query.find.by.team.id");
        List<TeamMessage> teamMessages = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, teamId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                teamMessages.add(DAOMapper.mapToTeamMessage(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teamMessages;
    }

    @Override
    public void save(TeamMessage message) {
        String query = resourcer.getString("team.message.query.save");

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, message.getTeam().getId());
            st.setLong(2, message.getUser().getId());
            st.setString(3, message.getMessage());
            st.setDate(4, new Date(System.currentTimeMillis()));

            if (message.getExpert().getId() != null) {
                st.setLong(5, message.getExpert().getId());
            } else {
                st.setNull(5, NULL);
            }

            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<TeamMessage> findActualMessagesByTeamIdAndUserId(Long teamId, Long userId) {
        String query = resourcer.getString("team.message.query.find.actual.by.team.id.user.id");
        List<TeamMessage> teamMessages = new ArrayList<>();

        try (PreparedStatement st = connection.prepareStatement(query)) {
            st.setLong(1, teamId);
            st.setLong(2, teamId);
            st.setLong(3, userId);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                teamMessages.add(DAOMapper.mapToTeamMessage(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teamMessages;
    }

    public static TeamMessageDAOImpl getInstance() {
        synchronized (TeamMessageDAOImpl.class) {
            if (instance == null) {
                instance = new TeamMessageDAOImpl();
            }
        }

        return instance;
    }
}
